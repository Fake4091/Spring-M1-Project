package com.example.Spring_Project.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) { this.userService = userService; }

  @GetMapping
  public List<User> getAllUsers() { return userService.getUsers(); }

  @GetMapping(path = "{userId}")
  public List<User> getUser(@PathVariable("userId") Long userId) { return userService.getUser(userId); }

  @PostMapping
  public void registerNewUser(@RequestBody User user) {
    userService.addNewUser(user);
  }

  @PutMapping(path = "{userId}")
  public void updateUser(@PathVariable("userId") Long userId, @RequestParam(required = false) String username, @RequestParam(required = true) String oldPassword, @RequestParam(required = false) String newPassword, @RequestParam(required = false) String email) {
    userService.updateUser(userId, username, oldPassword, newPassword, email);
  }

  @DeleteMapping(path = "{userId}")
  public void deleteUser(@PathVariable("userId") Long userId, @RequestParam(required = false) String password) {
    userService.deleteUser(userId);
  }

}
