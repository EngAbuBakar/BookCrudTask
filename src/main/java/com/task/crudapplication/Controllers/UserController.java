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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(this.userService.addUser(userDto), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(UserDto userDto) {
        List<UserDto> userList = (List<UserDto>) this.userService.getAllUsers();
        if (isEmpty(userList)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return new ResponseEntity<>(HttpStatus.FOUND);
    }

    private boolean isEmpty(List list){
        return list == null || list.isEmpty();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id) throws UserNotFound {
        User user = userService.getUserById(id);
        if (user == null) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            throw new UserNotFound("User not exist with the id:" + id);
        }

        return ResponseEntity.of(Optional.of(user));
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Long id) throws UserNotFound {
        User users = userService.getUserById(id);
        if (users == null) {
            throw new UserNotFound("user not found with the id:" + id);
        }
        userDto.setId(id);
        userService.updateUser(userDto, id);
        return users;
    }
    @PutMapping("/{userId}/books/{bookId}")
    public User assignBookToUser(@PathVariable("userId") Long uid,@PathVariable("bookId") Long bid){
        return userService.assignBookToUser(uid,bid);

    }

    @DeleteMapping("/{userId}")
  //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("userId") Long id) throws UserNotFound {
        // Book book=bookService.getBookById(id);
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFound("user not found with the id:" + id);
        }

        this.userService.deleteUser(id);
        return "User deleted";
    }


}
