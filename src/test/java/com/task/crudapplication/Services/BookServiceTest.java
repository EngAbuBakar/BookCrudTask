package com.task.crudapplication.Services;

import com.task.crudapplication.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        this.bookService=new BookService(this.bookRepository);
    }

    @Test
    void getAllBooks() {
        bookService.getAllBooks();
        verify(bookRepository).findAll();

    }
}