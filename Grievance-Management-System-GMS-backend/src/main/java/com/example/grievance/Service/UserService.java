package com.example.grievance.Service;

import com.example.grievance.Entity.User;
import com.example.grievance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(Long userId, User updatedUser) {
        // Directly set the userId and save, regardless of existence
        updatedUser.setUserId(userId);
        return userRepository.save(updatedUser);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role); // Fetch users by role
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
