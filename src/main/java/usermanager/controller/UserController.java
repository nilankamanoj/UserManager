package usermanager.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usermanager.model.User;
import usermanager.service.UserService;
import usermanager.util.UserValidator;
import usermanager.util.CustomResponse;
import java.util.HashMap;


@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Bean
    private DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setCustomFieldMapper((source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null);
        return mapper;
    }
@RequestMapping(path="/", method = RequestMethod.GET)
    public ResponseEntity <?> getAllUsers() {
   return new ResponseEntity<>(new CustomResponse(200,"success",userService.findAll()),HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity <?>  addUser(@RequestBody User user) {
        System.out.println(user.getFirstName());
        HashMap err = userValidator.validate(user);
        if(err.isEmpty()) {
            return new ResponseEntity<>(new CustomResponse(200,"success",userService.save(user)), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new CustomResponse(400,err,null), HttpStatus.BAD_REQUEST);
        }
        }


    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public ResponseEntity <?> getOne(@PathVariable String id) {
        int Id;
    try{
        Id = Integer.parseInt(id);
    }catch (Exception NumberFormatException){
        return new ResponseEntity<>(new CustomResponse(400,"invalid data type for ID",null), HttpStatus.BAD_REQUEST);
    }
            User user = userService.findOne(Id);


            if (user != null) {
                return new ResponseEntity<>(new CustomResponse(200,"success",user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponse(400,"user does not exist",null), HttpStatus.BAD_REQUEST);
            }

    }
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable String id) {
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            return new ResponseEntity<>(new CustomResponse(400,"invalid data type for ID",null), HttpStatus.BAD_REQUEST);
        }
        User user = userService.findOne(Id);
        if (user != null) {
            userService.deleteOne(Id);
            return new ResponseEntity<>(new CustomResponse(200,"success",user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomResponse(400,"user does not exist",null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOne(@RequestBody User user, @PathVariable String id) {
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            return new ResponseEntity<>(new CustomResponse(400,"invalid data type for ID",null), HttpStatus.BAD_REQUEST);
        }
        user.setId(Id);
        User oldUser = userService.findOne(Id);
        if(oldUser!=null) {
            dozerBeanMapper().map(user,oldUser);
            HashMap err = userValidator.validate(oldUser);
            if(err.isEmpty()) {
                User updatedUser = userService.updateOne(oldUser);
                return new ResponseEntity<>(new CustomResponse(200,"success",updatedUser), HttpStatus.OK);
            }

            else {
                return new ResponseEntity<>(new CustomResponse(400,err,null), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(new CustomResponse(400,"user does not exist",null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/active" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllActive() {
        return new ResponseEntity<>(new CustomResponse(200,"success",userService.findAllByIsActive(true)),HttpStatus.OK);
    }

    @RequestMapping(path="/inactive" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllInActive() {
        return new ResponseEntity<>(new CustomResponse(200,"success",userService.findAllByIsActive(false)),HttpStatus.OK);
    }

}