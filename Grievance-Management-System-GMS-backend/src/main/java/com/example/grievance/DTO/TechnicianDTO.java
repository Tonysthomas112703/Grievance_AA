package com.example.grievance.DTO;

public class TechnicianDTO {
    private Long id;      // Assuming userId is Long
    private String username;
    private String role;

    // Constructor with three parameters
    public TechnicianDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;  // Added the role field
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
