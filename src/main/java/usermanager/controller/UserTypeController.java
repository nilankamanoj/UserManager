package usermanager.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usermanager.model.UserType;
import usermanager.service.UserTypeService;
import usermanager.util.CustomResponse;
import usermanager.util.UserTypeValidator;

import java.util.HashMap;


@Controller
@RequestMapping(path="/usertypes")
public class UserTypeController {
    @Autowired
    private UserTypeService userTypeService;
    @Autowired
    private UserTypeValidator userTypeValidator;
    @Bean
    private DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setCustomFieldMapper((source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null);
        return mapper;
    }

    @RequestMapping(path="/" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserTypes() {
        return new ResponseEntity<>(new CustomResponse(200,"success",userTypeService.findAll()),HttpStatus.OK);
    }

    @RequestMapping(path="/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserTypes(@PathVariable String id) {
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            return new ResponseEntity<>(new CustomResponse(400,"invalid data type for ID",null), HttpStatus.BAD_REQUEST);
        }
        UserType userType = userTypeService.findOne(Id);
        if(userType!=null){
            return new ResponseEntity<>(new CustomResponse(200,"success",userType), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new CustomResponse(400,"user type does not exist",null), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody UserType userType) {
        HashMap err = userTypeValidator.validate(userType);
        if(err.isEmpty()){
            userTypeService.save(userType);
            return new ResponseEntity<>(new CustomResponse(200,"success",userType),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new CustomResponse(400,err,null),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable String id) {
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            return new ResponseEntity<>(new CustomResponse(400,"invalid data type for ID",null), HttpStatus.BAD_REQUEST);
        }
        UserType userType = userTypeService.findOne(Id);
        if(userType!=null){
            userTypeService.deleteOne(Id);
            return new ResponseEntity<>(new CustomResponse(200,"success",userType), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new CustomResponse(400,"user type does not exist",null), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path="/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<?> updateOne(@RequestBody UserType userType, @PathVariable String id) {
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            return new ResponseEntity<>(new CustomResponse(400,"invalid data type for ID",null), HttpStatus.BAD_REQUEST);
        }
        UserType oldUserType = userTypeService.findOne(Id);
        if(oldUserType!=null){
            dozerBeanMapper().map(userType,oldUserType);
            HashMap err = userTypeValidator.validate(oldUserType);
            if(err.isEmpty()){
                UserType updatedUserType = userTypeService.update(oldUserType);
                return new ResponseEntity<>(new CustomResponse(200,"success",updatedUserType), HttpStatus.OK);
            }
            return new ResponseEntity<>(new CustomResponse(400,err ,null), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(new CustomResponse(400,"user type does not exist",null), HttpStatus.BAD_REQUEST);
        }

    }
}
