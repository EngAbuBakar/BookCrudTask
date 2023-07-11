package com.task.crudapplication.Services;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.task.crudapplication.DTOs.BookDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.UserNotFound;
import com.task.crudapplication.Repository.BookRepository;
import com.task.crudapplication.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ModelMapper modelMapper;
    public Book addBook(BookDto bookDto)
    {
        Book book=Book.build(0L,bookDto.getTitle(),bookDto.getTier(),bookDto.getUser());
        this.bookRepository.save(book);
        return book;
    }

    public List<BookDto> getAllBooks() {
        List<Book>books=this.bookRepository.findAll();
        List<BookDto> bookDtos=books.stream().map(this::BookToDto).collect(Collectors.toList());
            return bookDtos;
        }


    public Book getBookById(Long id){
        Book book=null;
        try {
            book=  this.bookRepository.findById(id).get();
        } catch (Exception e){
            e.printStackTrace();
        }

        return book;
    }

    public String updateBook(Book book, Long id) {
        Book updatedbook=null;
        try {
            this.bookRepository.save(book);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "Book Updated Successfully";
    }

    public String deleteBook(Long id) {
        try {
            Optional<Book> book = this.bookRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.bookRepository.deleteById(id);
        return "Book deleted";
    }

    public Book dtoToBook(BookDto bookDto){
     Book book=this.modelMapper.map(bookDto,Book.class);
      return book;
    }

    public BookDto BookToDto(Book book){
        BookDto bookDto=this.modelMapper.map(book,BookDto.class);
        return bookDto;
    }




}
