package com.veracity.LibraryManagmentSystem.Controller;

import com.veracity.LibraryManagmentSystem.DTO.AuthResponse;
import com.veracity.LibraryManagmentSystem.DTO.LoginRequest;
import com.veracity.LibraryManagmentSystem.DTO.RegisterRequest;
import com.veracity.LibraryManagmentSystem.Service.AuthService;
import com.veracity.LibraryManagmentSystem.Entity.User;

import com.veracity.LibraryManagmentSystem.Service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTService jwtService;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JWTService jwtService,
                          AuthService authService) {

        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {

        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );


        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());


        String token =
                jwtService.generateToken(userDetails.getUsername());


        AuthResponse response = new AuthResponse();

        response.setMessage("Login Successful");
        response.setToken(token);
        response.setUsername(userDetails.getUsername());

        // Get Role (ROLE_ADMIN -> ADMIN)
        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        response.setRole(role);

        return response;
    }
}