package com.veracity.LibraryManagmentSystem.Service;

import com.veracity.LibraryManagmentSystem.DTO.UserResponse;
import com.veracity.LibraryManagmentSystem.Entity.User;
import com.veracity.LibraryManagmentSystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All Users
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    // Get User By id
    public UserResponse getUserById(Long id) {
        User user =userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found : " + id));

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    // Add User
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Update User
    public User updateUser(Long id, User userDetails) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found : " + id));

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setRole(userDetails.getRole());

        return userRepository.save(existingUser);
    }

    // Delete User
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found : " + id));

        userRepository.delete(existingUser);
    }
}