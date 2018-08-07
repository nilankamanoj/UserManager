package usermanager.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.dozer.DozerBeanMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usermanager.model.User;
import usermanager.service.UserService;
import usermanager.util.AddUserValidator;
import usermanager.util.CustomResponse;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.validation.Valid;
import java.util.HashMap;


@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddUserValidator addUserValidator;
    //private DozerBeanMapper dozerBeanMapper = new  DozerBeanMapper();
//    @RequestMapping(path="/", method = RequestMethod.GET)
//    public @ResponseBody Iterable<User> getAllUsers() {
//        return userService.findAll();
//    }
    @Bean
    private DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setCustomFieldMapper((source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null);
        return mapper;
    }
@RequestMapping(path="/", method = RequestMethod.GET)
    public ResponseEntity <?> getAllUsers() {
    CustomResponse customResponse = new CustomResponse();
    customResponse.setStatus(200);
    customResponse.setMessage("success");
    customResponse.setData(userService.findAll());
        return new ResponseEntity<>(customResponse,HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity <?>  addUser(@RequestBody User user) {
        HashMap err = addUserValidator.validate(user);
        CustomResponse customResponse = new CustomResponse();
        if(err.isEmpty()) {
            user = userService.save(user);
            customResponse.setStatus(200);
            customResponse.setMessage("success");
            customResponse.setData(user);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
        else{
            customResponse.setStatus(400);
            customResponse.setMessage(err);
            customResponse.setData(null);
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        }


    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public ResponseEntity <?> getOne(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();
        int Id;
    try{
        Id = Integer.parseInt(id);
    }catch (Exception NumberFormatException){
        customResponse.setStatus(400);
        customResponse.setMessage("Invalid data type for user id");
        customResponse.setData(null);
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }
            User user = userService.findOne(Id);


            if (user != null) {
                customResponse.setStatus(200);
                customResponse.setMessage("success");
                customResponse.setData(user);
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setStatus(400);
                customResponse.setMessage("user dosen't exists!");
                customResponse.setData(null);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

    }
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            customResponse.setStatus(400);
            customResponse.setMessage("Invalid data type for user id");
            customResponse.setData(null);
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        User user = userService.findOne(Id);
        if (user != null) {
            userService.deleteOne(Id);
            customResponse.setStatus(200);
            customResponse.setMessage("success");
            customResponse.setData(user);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } else {
            customResponse.setStatus(400);
            customResponse.setMessage("user dosen't exists!");
            customResponse.setData(null);
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOne(@RequestBody User user, @PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();
        int Id;
        try{
            Id = Integer.parseInt(id);
        }catch (Exception NumberFormatException){
            customResponse.setStatus(400);
            customResponse.setMessage("Invalid data type for user id");
            customResponse.setData(null);
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        user.setId(Id);

        User oldUser = userService.findOne(Id);
        if(oldUser!=null) {
            //dozerBeanMapper.setCustomFieldMapper((source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null);
            //dozerBeanMapper.map(user,oldUser);
            dozerBeanMapper().map(user,oldUser);

            HashMap err = addUserValidator.validate(oldUser);
            if(err.isEmpty()) {
                User updatedUser = userService.updateOne(oldUser);
                customResponse.setStatus(200);
                customResponse.setMessage("success");
                customResponse.setData(updatedUser);
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            }

            else {
                customResponse.setStatus(400);
                customResponse.setMessage(err);
                customResponse.setData(null);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            customResponse.setStatus(400);
            customResponse.setMessage("user dosen't exists!");
            customResponse.setData(null);
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/active" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllActive() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus(200);
        customResponse.setMessage("success");
        customResponse.setData(userService.findAllByIsActive(true));
        return new ResponseEntity(customResponse,HttpStatus.OK);
    }

    @RequestMapping(path="/inactive" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllInActive() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setStatus(200);
        customResponse.setMessage("success");
        customResponse.setData(userService.findAllByIsActive(false));
        return new ResponseEntity(customResponse,HttpStatus.OK);
    }

}