package com.veracity.LibraryManagmentSystem.Repository;

import com.veracity.LibraryManagmentSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
