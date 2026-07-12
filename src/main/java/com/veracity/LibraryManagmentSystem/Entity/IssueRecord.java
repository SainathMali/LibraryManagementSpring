package com.veracity.LibraryManagmentSystem.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "IssueRecord")
@Data
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private Boolean returned;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}