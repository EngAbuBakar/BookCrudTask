package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.BookDto;
import com.task.crudapplication.DTOs.UserDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.UserNotFound;
import com.task.crudapplication.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private UserService userService;
    User mockUser;
    UserDto sampleUserDto;
    BookDto bookDto;


    @BeforeEach
    void setUp() {
        this.userService = new UserService(this.userRepository);
        // Initialize sample test data before each test
        sampleUserDto = new UserDto();
        sampleUserDto.setId(1L);
        sampleUserDto.setName("John Doe");
        sampleUserDto.setEmail("john@example.com");
        sampleUserDto.setMembershipId(1L);
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(1L, "Book 1", "gold", sampleUserDto));
        bookDtos.add(new BookDto(2L, "Book 2", "Bronze", sampleUserDto));
        sampleUserDto.setBookDto(bookDto);

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("John Doe");
        mockUser.setEmail("john@example.com");
        mockUser.setMembershipId(1L);
        List<Book> books = new ArrayList<>();
        // books.add(new Book(1L, "Book 1","gold",sampleUserDto));
        // books.add(new Book(2L, "Book 2","Bronze",sampleUserDto));

    }
        @Test
        public void testAddUser () {
            when(userRepository.save(mockUser)).thenReturn(mockUser);
            when(modelMapper.map(sampleUserDto, User.class)).thenReturn(mockUser);
            User addedUser = userService.addUser(sampleUserDto);


        }


    @Test
    void getAllUsers() throws UserNotFound {
        userService.getAllUsers();
        verify(userRepository).findAll();

    }

    @Test
    public void testGetUserById() throws UserNotFound {
        Long id = 1L;

        mockUser = new User();
        mockUser.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(id);
        verify(userRepository).findById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setName("John Doe");
        userDto.setEmail("john@example.com");
        userDto.setMembershipId(2L);

        userDto.setBookDto(bookDto);

        User updatedUser = User.build(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getMembershipId(), (List<Book>) userDto.getBookDto());
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        userService.updateUser(userDto, userId);

        verify(userRepository, times(1)).findById(userId);

        //verify(userRepository, times(1)).save(updatedUser);
    }
}