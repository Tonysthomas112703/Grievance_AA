package com.example.grievance.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Correctly maps to 'user_id' column
    private Long userId; // Auto-incrementing user ID

    @Column(name = "username", nullable = false) // Maps to 'user_name' column, ensuring it is not null
    private String userName; // User's name

    @Column(name = "password", nullable = false) // Maps to 'password' column, ensuring it is not null
    private String password; // User's password


    @Column(name = "role", nullable = false) // Maps to 'role' column, ensuring it is not null
    private String role; // User's role (e.g., supervisor, consumer, assignee)

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
