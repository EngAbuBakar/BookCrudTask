package com.task.crudapplication.Controllers;

import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

@Autowired
    UserService userService;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        this.userService.addUser(user);
        return user;
    }

    @GetMapping("/getAllUsers")
    public List <User> getAllUsers(User user){
       List<User> userList=(List<User>) this.userService.getAllUsers();
       return userList;
    }
    @GetMapping (value = "getUserById/{id}")
    private User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }
}
