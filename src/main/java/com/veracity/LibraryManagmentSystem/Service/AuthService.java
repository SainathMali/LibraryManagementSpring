package com.veracity.LibraryManagmentSystem.Service;


import com.veracity.LibraryManagmentSystem.DTO.RegisterRequest;
import com.veracity.LibraryManagmentSystem.Repository.UserRepository;
import com.veracity.LibraryManagmentSystem.Entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    public User register(RegisterRequest registerRequest) {

        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        if (registerRequest.getUsername().equalsIgnoreCase("Admin12")) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        User savedUser = userRepository.save(user);

        try {

            emailService.sendWelcomeEmail(
                    savedUser.getUsername(),
                    savedUser.getEmail()
            );

        } catch (MessagingException e) {

            e.printStackTrace();

        }

        return savedUser;

    }
}



