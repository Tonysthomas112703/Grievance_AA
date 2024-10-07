package com.example.grievance.Repository;

import com.example.grievance.Entity.User; // Import your User model
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName); // Method to find user by username

    Optional<User> findByUserNameAndPasswordAndRole(String userName, String password, String role);
    List<User> findByRole(String role); // Fetch users by role

}
