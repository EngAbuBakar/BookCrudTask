package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.BookDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ModelMapper modelMapper;

    public Book addBook(BookDto bookDto) {
        Book book = Book.build(bookDto.getId(), bookDto.getTitle(), bookDto.getTier(),(List<User>) bookDto.getUserDto());
        this.bookRepository.save(book);
        return book;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        List<BookDto> bookDtos = books.stream().map(this::BookToDto).collect(Collectors.toList());
        return bookDtos;
    }


    public Book getBookById(Long id) {
        Book book = null;
        try {
            book = this.bookRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    public void updateBook(BookDto bookDto, Long id) {
        Book book = Book.build(bookDto.getId(), bookDto.getTitle(), bookDto.getTier(), (List<User>) bookDto.getUserDto());
        this.bookRepository.findById(id);
        try {
            this.bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String deleteBook(Long id) {
        try {
            Optional<Book> book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.bookRepository.deleteById(id);
        return "Book deleted";
    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book dtoToBook(BookDto bookDto) {
        Book book = this.modelMapper.map(bookDto, Book.class);
        return book;
    }

    public BookDto BookToDto(Book book) {
        BookDto bookDto = this.modelMapper.map(book, BookDto.class);
        return bookDto;
    }


}
