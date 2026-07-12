package com.veracity.LibraryManagmentSystem.Controller;

import com.veracity.LibraryManagmentSystem.DTO.BookResponse;
import com.veracity.LibraryManagmentSystem.Entity.Book;
import com.veracity.LibraryManagmentSystem.Service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@SecurityRequirement(name = "Bearer Authentication")
public class BookController {


    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // Get All Books
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "4") int size

    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    // Get Book By Id
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // Add Book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // Update Book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book bookDetails) {

        return ResponseEntity.ok(bookService.updateBook(id,bookDetails));
    }

    // Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return ResponseEntity.ok("Book Deleted Successfully");
    }


}