package com.example.Spring_Project.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibUserRepository
    extends JpaRepository<LibUser, Long> {
  Optional<LibUser> findUserByEmail(String email);
}
