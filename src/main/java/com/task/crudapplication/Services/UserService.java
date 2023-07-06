package com.task.crudapplication.Services;

import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User addUser(User user)
    {

       this.userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
      return this.userRepository.findAll();
    }
    public User getUserById(Long id){
        return this.userRepository.findById(id).get();
    }
}
