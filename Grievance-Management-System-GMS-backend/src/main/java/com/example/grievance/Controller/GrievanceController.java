package com.example.grievance.Controller;

import com.example.grievance.Entity.Grievance;
import com.example.grievance.Service.UserGrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:3000")
@RequestMapping("/api/grievances")
public class GrievanceController {

    @Autowired
    private UserGrievanceService grievanceService;

    // Create a new grievance
    @PostMapping("/create")
    public ResponseEntity<String> createGrievance(@RequestBody Grievance grievance) {
        try {
            grievance.setDate(LocalDateTime.now()); // Set current date and time
            grievanceService.createGrievance(grievance); // Call the service to save grievance
            return new ResponseEntity<>("Grievance created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create grievance.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get grievances by consumer name
    @GetMapping("/find")
    public ResponseEntity<List<Grievance>> getGrievancesByConsumerName(@RequestParam String consumerName) {
        try {
            List<Grievance> grievances = grievanceService.getGrievancesByConsumerName(consumerName);
            if (grievances.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(grievances, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
