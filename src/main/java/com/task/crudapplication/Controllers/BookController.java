package com.task.crudapplication.Controllers;

import com.task.crudapplication.DTOs.BookDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.BookNotFound;
import com.task.crudapplication.Exceptions.MembershipNotFound;
import com.task.crudapplication.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookDto bookDto) {

        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);

    }

    @GetMapping("/")
    //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<BookDto>> getAllBooks(BookDto bookDto) {
        List<BookDto> bookList = (List<BookDto>) bookService.getAllBooks();
        if (isEmpty(bookList)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    private boolean isEmpty(List list){
        return list == null || list.isEmpty();
    }

    @GetMapping(value = "/{bookId}")
//    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long id) throws BookNotFound {
        Book book = bookService.getBookById(id);
        if (book == null) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            throw new BookNotFound("Book not found with the id:" + id);
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable("bookId") Long id, @RequestBody BookDto bookDto) throws BookNotFound {
        Book books = bookService.getBookById(id);
        if (books == null) {
            // return "Book is Not found";
            throw new BookNotFound("Book not found with the id:" + id);
        }
        bookDto.setId(id);
        this.bookService.updateBook(bookDto, id);
        return books;
    }

    @DeleteMapping("/{bookId}")
    //   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteBook(@PathVariable("bookId") Long id) throws BookNotFound {
        Book book = bookService.getBookById(id);
        if (book == null) {
            throw new BookNotFound("Book is not found with the id:" + id);
        }
        this.bookService.deleteBook(id);
        return "Book deleted";
    }

}
