package usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usermanager.model.UserType;
import usermanager.service.UserTypeService;


@Controller
@RequestMapping(path="/usertypes")
public class UserTypeController {
    @Autowired
    private UserTypeService userTypeService;

    @RequestMapping(path="/" , method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UserType> getAllUserTypes() {
        return userTypeService.findAll();
    }

    @RequestMapping(path="/{id}" , method = RequestMethod.GET)
    public @ResponseBody
    UserType getAllUserTypes(@PathVariable Integer id) {
        return userTypeService.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserType> update(@RequestBody UserType userType) {
        userTypeService.save(userType);
        return new ResponseEntity<UserType>(userType,HttpStatus.OK);
    }
    @RequestMapping(path="/{id}" , method = RequestMethod.DELETE)
    public @ResponseBody
    UserType deleteOne(@PathVariable Integer id) {
        UserType userType = userTypeService.findOne(id);
        userTypeService.deleteOne(id);
        return userType;
    }
}
