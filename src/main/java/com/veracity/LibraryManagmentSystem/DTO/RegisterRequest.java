package com.veracity.LibraryManagmentSystem.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username is required and cannot be blank")
    private String username;


    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password must be at least 5 characters long")

    private String password;
}
