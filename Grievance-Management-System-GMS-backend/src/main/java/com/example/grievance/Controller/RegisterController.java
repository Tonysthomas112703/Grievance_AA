package com.example.grievance.Controller;

import com.example.grievance.DTO.UserRegistrationDTO;
import com.example.grievance.Entity.User;
import com.example.grievance.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:3000")
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDto) {
        try {
            // Create a new User object and populate it with the request parameters
            User user = new User();
            user.setUserName(userRegistrationDto.getUsername());
            user.setPassword(userRegistrationDto.getPassword());
            user.setRole(userRegistrationDto.getRole());

            // Register the user
            User registeredUser = registerService.registerUser(user);

            // Return user ID on successful registration
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser.getUserId());
        } catch (RuntimeException e) {
            // Return conflict status if username already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed!");
        }
    }
}
