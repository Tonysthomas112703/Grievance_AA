package com.example.grievance.Service;

import com.example.grievance.Entity.User;
import com.example.grievance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public String login(String userName, String password, String role) {
        // Fetch the user with matching username, password, and role
        Optional<User> user = userRepository.findByUserNameAndPasswordAndRole(userName, password, role);

        // If user is found, login is successful
        if (user.isPresent()) {
            return "Login successful for role: " + role;
        } else {
            // If user is not found, return an error message
            throw new RuntimeException("Invalid credentials or role");
        }
    }
}
