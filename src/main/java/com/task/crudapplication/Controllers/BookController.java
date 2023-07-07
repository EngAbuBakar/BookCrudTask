package com.task.crudapplication.Controllers;

import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book){
      this.bookService.addBook(book);
        return book;
    }
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(Book book){
       List<Book> bookList=(List<Book>) this.bookService.getAllBooks();
        return bookList;
    }
    @GetMapping (value = "getBookById/{id}")
    private Book getBook(@PathVariable("id") Long id){
        return bookService.getBookById(id);
    }

    @PutMapping("updateBookById/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book ){
        book.setId(id);
        this.bookService.updateBook(book,id);
        return "Book updated successfully";
    }

    @DeleteMapping("deleteBookById/{id}")
    public String deleteBook(@PathVariable Long id){
        this.bookService.deleteBook(id);
        return "Book deleted";
    }

}
