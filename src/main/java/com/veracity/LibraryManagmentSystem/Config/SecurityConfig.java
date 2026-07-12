package com.veracity.LibraryManagmentSystem.Config;

import com.veracity.LibraryManagmentSystem.Filter.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;

    public SecurityConfig(JWTAuthFilter jwtAuthFilter) {

        this.jwtAuthFilter = jwtAuthFilter;
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    // Security Rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF
                .csrf(csrf -> csrf.disable())


                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers("/auth/register", "/auth/login")
                        .permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Books
                        .requestMatchers(HttpMethod.GET, "/books/**")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/books")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/books/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/books/**")
                        .hasRole("ADMIN")

                        // Users
                        .requestMatchers("/users/**")
                        .hasRole("ADMIN")

                        // Issue APIs
                        .requestMatchers("/issues/**")
                        .hasAnyRole("USER", "ADMIN")

                        // Any other request
                        .anyRequest()
                        .authenticated()
                )

                // Disable Basic Authentication
                .httpBasic(httpBasic -> httpBasic.disable())

                // Disable Default Login Form
                .formLogin(form -> form.disable())

                // JWT Filter
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}