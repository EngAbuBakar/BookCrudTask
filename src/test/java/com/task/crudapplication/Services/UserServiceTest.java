package com.task.crudapplication.Services;

import com.task.crudapplication.Exceptions.UserNotFound;
import com.task.crudapplication.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        this.userService=new UserService(this.userRepository);
    }

    @Test
    void getAllUsers() throws UserNotFound {
        userService.getAllUsers();
        verify(userRepository).findAll();

    }
}