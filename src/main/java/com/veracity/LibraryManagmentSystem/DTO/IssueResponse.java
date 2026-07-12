package com.veracity.LibraryManagmentSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueResponse {

    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean returned;

    private Long userId;
    private String username;
    private String email;


    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
}
