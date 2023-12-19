package com.examination.app.service;

import com.examination.app.dto.LoginResponseDTO;
import com.examination.app.dto.UserDTO;
import com.examination.app.entity.User_App;
import com.examination.app.exception.ResourceAlreadyExistException;
import com.examination.app.exception.ResourceNotFoundException;
import com.examination.app.repository.UserRepository;
import com.examination.app.util.JWTUtil;
import com.examination.app.util.UserRoleEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

   
    public UserDTO registerUser(UserDTO userDTO) {
        // Check if the email is already registered
        if (userRepository.existsByEmail(userDTO.getEmail()))
            throw new ResourceAlreadyExistException("Email already registered.");

        // Convert UserDTO to User entity, encode password, and save to the repository
        User_App user = userDTO.toObject();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
     

        // Return UserDTO representation of the saved user
        return UserDTO.toDTO(user);
    }

 
    public LoginResponseDTO signIn(String email, String password) {
        // Authenticate the user with the provided credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        // Retrieve user details from the repository
        User_App user = userRepository.findUserByEmail(email).orElseThrow(ResourceNotFoundException::new);

        // Load UserDetails and generate a JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(jwtUtil.generateToken(userDetails));
        loginResponseDTO.setUser(user);

        // Return the LoginResponseDTO containing the JWT token and user details
        return loginResponseDTO;
    }

    
    public String signOut() {
        // Placeholder for sign-out logic, such as invalidating tokens or session management
        return "Sign out successful";
    }
}
