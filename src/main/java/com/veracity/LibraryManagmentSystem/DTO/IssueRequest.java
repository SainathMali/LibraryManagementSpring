package com.veracity.LibraryManagmentSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRequest {


    private Long userId;

    private Long bookId;

}
