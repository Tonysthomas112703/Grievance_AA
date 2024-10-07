package com.example.grievance.Controller;

import com.example.grievance.DTO.TechnicianDTO;
import com.example.grievance.Entity.Grievance;
import com.example.grievance.Entity.User; // Assuming you have a User entity
import com.example.grievance.Service.UserGrievanceService;
import com.example.grievance.Service.UserService; // Assuming you have a UserService for managing users
import com.example.grievance.Service.SupervisorService; // Importing SupervisorService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:3000")
@RequestMapping("/api/supervisor")
public class SupervisorController {

    @Autowired
    private UserGrievanceService userGrievanceService;

    @Autowired
    private UserService userService; // Service to manage user operations

    @Autowired
    private SupervisorService supervisorService; // Correctly autowiring SupervisorService

    // Get all grievances
    @GetMapping("/grievances")
    public ResponseEntity<List<Grievance>> getAllGrievances() {
        try {
            List<Grievance> grievances = userGrievanceService.getAllGrievances();
            return new ResponseEntity<>(grievances, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all technicians
    @GetMapping("/technicians")
    public ResponseEntity<List<TechnicianDTO>> getAllTechnicians() {
        try {
            List<User> technicians = userService.getUsersByRole("ASSIGNEE"); // Fetch users with the role of 'ASSIGNEE'
            // Convert to DTO
            List<TechnicianDTO> technicianDTOs = technicians.stream()
                    .map(tech -> new TechnicianDTO(tech.getUserId(), tech.getUserName(), tech.getRole())) // Map User to TechnicianDTO
                    .toList(); // Collect the results into a List
            return new ResponseEntity<>(technicianDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/assign")
    public ResponseEntity<String> assignAssigneeToGrievance(
            @RequestParam String complaintId,
            @RequestParam String assigneeId  ){
        try {
            // Assign the AssigneeId to the grievance
            Grievance updatedGrievance = supervisorService.assignAssignee(complaintId, assigneeId); // Use instance method

            return new ResponseEntity<>("Assignee assigned to grievance successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to assign assignee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
