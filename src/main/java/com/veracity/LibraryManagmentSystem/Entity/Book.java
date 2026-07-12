package com.veracity.LibraryManagmentSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @NotBlank(message = "Title can not be Empty")
    private String title;

    @NotBlank(message = "Author Can not be Empty")
    private String author;

    @NotNull(message = "Quantity is required")
    @Min(value = 1,message = "Quantity must be at least 1")
    private Integer quantity;
}