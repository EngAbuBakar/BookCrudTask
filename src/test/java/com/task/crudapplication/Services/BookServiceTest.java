package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.BookDto;
import com.task.crudapplication.DTOs.UserDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Repository.BookRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private BookService bookService;
    private static final Long id = 2L;
    private static final String title = "Python";
    private static final String tier = "gold";
    private static final User user = new User();

    Book book;
   // User user;
    UserDto userDto;
    BookDto bookDto;
    Book mockBook;

    @BeforeEach
    void setUp() {
        this.bookService = new BookService(this.bookRepository);
        bookDto =new BookDto();
        bookDto.setId(4L);
        bookDto.setTier("gold");
        bookDto.setTitle("Python");

        List<UserDto> userList = new ArrayList<>();
      //  userList.add()
        new UserDto(id, "Rizwan", "xyz@email.com", 2L,bookDto );
     List<User>  users=new ArrayList<>();
      user.setId(id);
      user.setName("Rizwan");
      users.add(user);
      user.setEmail("xyz@gmail.com");
      user.setMembershipId(2L);
     // user.setAssignedBooks();

//        List<User>users=new ArrayList<>();
//        users.add(new User(id,"Rizwan","xyz@gmail.com",2L,bookDto));

         book = new Book();
        book.setId(1L);
        book.setTitle("python");
        book.setTier("gold");
        book.setUserList(users);
       // bookService.addBook(userDto.getBookDto());
    }

    @Test
    void testGetAllBooks() {
        BookDto bookDto = new BookDto(id, title, tier, new UserDto(user.getId(), user.getName(), user.getEmail(),
                user.getMembershipId(), new BookDto()));
         //when(bookRepository.findAll()).thenReturn(Stream.of(new Book(id,title,tier,user)).collect(Collectors.toList()));
        bookService.getAllBooks();
        verify(bookRepository).findAll();
    }

    @Test
    public void testGetBookById() {
        Long id = 1L;

        mockBook = new Book();
        mockBook.setId(id);
        when(bookRepository.findById(id)).thenReturn(Optional.of(mockBook));
        Book result = bookService.getBookById(id);
        verify(bookRepository).findById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());

    }

    @Test
    void testAddBook() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Python");
        bookDto.setTier("gold");
      //  bookDto.setUserDto(userDto.getBookDto().getUserDto());
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(1L, book.getId());
        assertEquals("python",book.getTitle());
    }

    @Test
    public void testUpdateBook() {
        Long bookId = 1L;
        BookDto bookDto = new BookDto();
        bookDto.setId(bookId);
        bookDto.setTitle("Test Book");
        bookDto.setTier("High");
        bookDto.setUserDto(userDto);

        mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setTitle("Test Book");
        mockBook.setTier("High");
        Book updatedBook = Book.build(bookDto.getId(), bookDto.getTitle(), bookDto.getTier(), (List<User>) bookDto.getUserDto());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));
       // when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        bookService.updateBook(bookDto, bookId);

        // Verify that the repository's findById method was called with the correct bookId
        verify(bookRepository).findById(bookId);
        verify(bookRepository, times(1)).findById(bookId);

        //verify(bookRepository, times(1)).save(updatedBook);

    }



}