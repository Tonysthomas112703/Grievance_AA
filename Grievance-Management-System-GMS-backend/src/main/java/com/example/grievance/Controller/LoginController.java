package com.example.grievance.Controller;

import com.example.grievance.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(" http://127.0.0.1:3000")
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String role) {

        try {
            // Call the service method to handle login
            String response = loginService.login(userName, password, role);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Return error response if login fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
