package com.task.crudapplication.Controllers;

import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List <User> getAllUsers(@RequestHeader User user){

       List<User> userList=(List<User>) this.userService.getAllUsers();
       return userList;
    }
    @GetMapping ("getUserById/{id}")
    private User getUser(@PathVariable("id") Long id){

        return userService.getUserById(id);
    }

    @PutMapping("updateUserById/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
            user.setId(id );
            userService.updateuser(user,id);
        return user;
    }
    @DeleteMapping("deleteUserById/{id}")
    public String deleteUser(@PathVariable ("id") Long id){
       this.userService.deleteuser(id);
        return "User deleted";
    }



}
