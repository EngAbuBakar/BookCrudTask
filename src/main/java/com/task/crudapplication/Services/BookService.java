package com.task.crudapplication.Services;

import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Repository.BookRepository;
import com.task.crudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.awt.print.Book;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public Book addBook(Book book)
    {
        this.bookRepository.save(book);
        return book;
    }

    public List<Book> getAllBooks() {
            return this.bookRepository.findAll();
        }


    public Book getBookById(Long id){
        return this.bookRepository.findById(id).get();
    }

    public String updateBook(Book book, Long id) {
        this.bookRepository.save(book);
        return "Book Updated Successfully";
    }

    public String deleteBook(Long id) {
        this.bookRepository.deleteById(id);
        return "Book deleted";
    }
}
