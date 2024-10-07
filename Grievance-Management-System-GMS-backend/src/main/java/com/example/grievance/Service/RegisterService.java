package com.example.grievance.Service;

import com.example.grievance.Entity.User; // Import your User model
import com.example.grievance.Repository.UserRepository; // Import your User repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {


        // Save the user to the database
        return userRepository.save(user);
    }
}
