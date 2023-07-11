package com.task.crudapplication.Controllers;

import com.task.crudapplication.DTOs.BookDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.BookNotFound;
import com.task.crudapplication.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookDto bookDto){

     return new ResponseEntity<>(this.bookService.addBook(bookDto),HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks(BookDto bookDto){
       List<BookDto> bookList=(List<BookDto>) this.bookService.getAllBooks();
       if (bookList.size()<=0){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
        return ResponseEntity.of(Optional.of(bookList));
    }

    @GetMapping (value = "/{bookId}")
    private ResponseEntity<Book> getBook(@PathVariable("bookId") Long id){
        Book book= bookService.getBookById(id);
        if (book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book updatedBook ){
        Book book=bookService.getBookById(id);
        if (book==null){
            return "Book is Not found";
        }
        updatedBook.setId(id);
       this.bookService.updateBook(book,id);
       return "Book updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) throws BookNotFound {
        Book book=bookService.getBookById(id);
        if (book==null){
            return "Book not found";
        }
        this.bookService.deleteBook(id);
        return "Book deleted";
    }

}
