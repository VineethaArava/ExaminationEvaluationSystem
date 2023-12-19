package com.examination.app.controller;

import com.examination.app.service.UserService;
import com.examination.app.dto.BasicDTO;
import com.examination.app.dto.LoginRequestDTO;
import com.examination.app.dto.LoginResponseDTO;
import com.examination.app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a Spring MVC controller handling authentication-related requests.
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Autowired service responsible for user-related operations.
    @Autowired
    UserService userService;

    // Handle the registration endpoint for creating a new user.
    @PostMapping("/register")
    public ResponseEntity<BasicDTO<UserDTO>> registerUser(@RequestBody UserDTO userDTO) {
       
        UserDTO user = userService.registerUser(userDTO);
        return new ResponseEntity<>(new BasicDTO<>(true, "User successfully registered", user), HttpStatus.CREATED);
    }

    // Handle the login endpoint for authenticating a user.
    @PostMapping("/login")
    public ResponseEntity<BasicDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
       
        return ResponseEntity.ok(new BasicDTO<>(userService.signIn(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())));
    }
}
