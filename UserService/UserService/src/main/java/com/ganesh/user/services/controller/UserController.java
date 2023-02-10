package com.ganesh.user.services.controller;

import com.ganesh.user.services.model.User;
import com.ganesh.user.services.repository.UserRepository;
import com.ganesh.user.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSinglUser(@PathVariable String userId){
       User user = userService.getUser(userId);
       return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
       List<User> allUser = userService.getAllUser();
       return ResponseEntity.ok(allUser);
    }
}
