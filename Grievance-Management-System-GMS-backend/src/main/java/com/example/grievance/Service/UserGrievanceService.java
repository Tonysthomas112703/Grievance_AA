package com.example.grievance.Service;

import com.example.grievance.Entity.Grievance;
import com.example.grievance.Repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserGrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    public void createGrievance(Grievance grievance) {
        grievance.setDate(LocalDateTime.now());
        grievanceRepository.save(grievance); // Save the grievance to the database
    }

    public List<Grievance> getGrievancesByConsumerName(String consumerName) {
        return grievanceRepository.findByConsumerName(consumerName); // Fetch grievances by consumer name
    }

    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    public List<Grievance> getGrievancesByAssigneeId(String assigneeId) { // Corrected the method argument
        return grievanceRepository.findByAssigneeId(assigneeId); // Use the same variable name
    }

    public Optional<Grievance> getGrievanceById(String complaintId) {
        return grievanceRepository.findById(complaintId);
    }

    public Grievance updateGrievanceStatus(String complaintId, String newStatus) {
        // Retrieve the grievance by ID
        Optional<Grievance> grievanceOpt = grievanceRepository.findById(complaintId);
        if (grievanceOpt.isPresent()) {
            Grievance grievance = grievanceOpt.get();
            grievance.setStatus(newStatus); // Update the status
            return grievanceRepository.save(grievance); // Save the updated grievance
        } else {
            throw new RuntimeException("Grievance not found"); // Handle grievance not found
        }
    }
}
