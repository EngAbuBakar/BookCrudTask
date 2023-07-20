package com.task.crudapplication.Repository;

import com.task.crudapplication.Exceptions.UserNotFound;
import com.task.crudapplication.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
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