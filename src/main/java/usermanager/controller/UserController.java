package usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usermanager.model.User;
import usermanager.service.UserService;


@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody User addUser(@RequestBody User user) {
        user = userService.save(user);
        return user;
    }
    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody User getOne(@PathVariable Integer id) {
        User user = userService.findOne(id);
        return user;
    }
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody User deleteOne(@PathVariable Integer id) {
        User user = userService.findOne(id);
        userService.deleteOne(id);
        return user;

    }

    @RequestMapping(path="/active" , method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllActive() {
        return userService.findAllByIsActive(true);
    }

    @RequestMapping(path="/inactive" , method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllInActive() {
        return userService.findAllByIsActive(false);
    }

}