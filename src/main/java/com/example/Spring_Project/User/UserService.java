package com.example.Spring_Project.User;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User getUser(Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      throw new IllegalStateException("User with id " + userId + " does not exist.");
    }
    return userOptional.get();
  }

  public void addNewUser(User user) {
    Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
    if (userOptional.isPresent()) {
      throw new IllegalStateException("User with email " + user.getEmail() + " already exists.");
    }
    userRepository.save(user);
  }

  public void updateUser(Long userId, String username, String oldPassword, String newPassword, String email) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      throw new IllegalStateException("User with id " + userId + " does not exist.");
    }
    User user = userOptional.get();
    if (!user.getPassword().equals(oldPassword)) {
      throw new IllegalStateException("Incorrect username or password.");
    }
    if (username != null && !username.isEmpty() && !Objects.equals(user.getUsername(), username)) {
      user.setUsername(username);
    }
    if (newPassword != null && !newPassword.isEmpty() && !Objects.equals(user.getPassword(), newPassword)) {
      user.setPassword(newPassword);
    }
    if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)) {
      Optional<User> userOptional2 = userRepository.findUserByEmail(email);
      if (userOptional2.isPresent()) {
        throw new IllegalStateException("The email \"" + email + "\" is already in use.");
      }
      user.setEmail(email);
    }
    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    boolean exists = userRepository.existsById(userId);
    if (!exists) {
      throw new IllegalStateException("User with id " + userId + " does not exist.");
    }
    userRepository.deleteById(userId);
  }
}
