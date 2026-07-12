package com.veracity.LibraryManagmentSystem.Repository;

import com.veracity.LibraryManagmentSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
