package com.examination.app.service;

import com.examination.app.repository.UserRepository;
import com.examination.app.entity.User_App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Retrieve user details by email from the UserRepository
        Optional<User_App> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            User_App _user = user.get();
            // Create and return UserDetails with user's email, password, and an empty list of authorities (roles)
            return new org.springframework.security.core.userdetails.User(_user.getEmail(), _user.getPassword(), new ArrayList<>());
        }

        // Throw UsernameNotFoundException if the user is not found
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
