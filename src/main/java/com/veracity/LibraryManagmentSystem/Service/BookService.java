package com.veracity.LibraryManagmentSystem.Service;

import com.veracity.LibraryManagmentSystem.DTO.BookResponse;
import com.veracity.LibraryManagmentSystem.Entity.Book;
import com.veracity.LibraryManagmentSystem.Repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get All Books
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    // Get Book By Id
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found : " + id));

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor()
        );


    }

    // Add Book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Update Book
    public Book updateBook(Long id, Book bookDetails) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found : " + id));

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setQuantity(bookDetails.getQuantity());

        return bookRepository.save(existingBook);
    }

    // Delete Book
    public void deleteBook(Long id) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found : " + id));

        bookRepository.delete(existingBook);
    }
}