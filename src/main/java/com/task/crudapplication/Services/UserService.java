package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.UserDto;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.UserNotFound;
import com.task.crudapplication.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public User addUser(UserDto userDto) {
        User user = User.build(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getMembershipId());
        this.userRepository.save(user);
        return user;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(this::userToDto
        ).collect(Collectors.toList());
        return userDtos;
        // User membershipUser=new User();
        //return this.userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFound {
        User user = null;
        try {
            user = this.userRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(UserDto userDto, Long id) {
        User user = User.build(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getMembershipId() );
        this.userRepository.findById(id);
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return "user deleted";
    }


    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

}
