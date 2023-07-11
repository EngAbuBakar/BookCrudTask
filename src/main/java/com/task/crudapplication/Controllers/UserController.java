package com.task.crudapplication.Controllers;

import com.task.crudapplication.DTOs.UserDto;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.UserNotFound;
import com.task.crudapplication.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

@Autowired
    UserService userService;
    @PostMapping()
    public ResponseEntity <User> addUser(@RequestBody @Valid UserDto userDto){
       return new ResponseEntity<>(this.userService.addUser(userDto),HttpStatus.CREATED) ;

    }

    @GetMapping("/")
    public ResponseEntity <List<UserDto>> getAllUsers( UserDto userDto){
       List<UserDto> userList=(List<UserDto>) this.userService.getAllUsers();
       if (userList.size()<=0){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       else
       return ResponseEntity.of(Optional.of(userList));
    }
    @GetMapping ("/{userId}")
    private ResponseEntity<User> getUser(@PathVariable("userId") Long id) throws UserNotFound {
        User user=userService.getUserById(id);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else new UserNotFound("User not available with the id:"+id);

        return ResponseEntity.of(Optional.of(user));
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
            user.setId(id );
            userService.updateuser(user,id);
        return user;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable ("userId") Long id) throws UserNotFound {
        // Book book=bookService.getBookById(id);
        User user=userService.getUserById(id);
        if (user==null){
            return "User not found";
        }
       this.userService.deleteuser(id);
        return "User deleted";
    }



}
