package com.example.grievance.Service;

import com.example.grievance.Entity.Grievance;
import com.example.grievance.Repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupervisorService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    public Grievance assignAssignee(String complaintId, String assigneeId) {
        Optional<Grievance> grievanceOptional = grievanceRepository.findById(complaintId); // Use instance method

        if (grievanceOptional.isPresent()) {
            Grievance grievance = grievanceOptional.get();
            grievance.setAssigneeId(assigneeId); // Set the AssigneeId
            return grievanceRepository.save(grievance); // Save the updated grievance
        } else {
            throw new RuntimeException("Grievance not found"); // Handle grievance not found
        }
    }
}
