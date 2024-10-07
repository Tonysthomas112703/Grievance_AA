package com.example.grievance.Controller;

import com.example.grievance.Entity.Grievance;
import com.example.grievance.Service.UserGrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:3000")
@RequestMapping("/api/assignee")
public class AssigneeController {

    @Autowired
    private UserGrievanceService userGrievanceService;

    // Update grievance status
    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateGrievanceStatus(
            @RequestParam String complaintId,
            @RequestParam String newStatus) {
        try {
            // Fetch the grievance by ID
            Grievance grievance = userGrievanceService.getGrievanceById(complaintId)
                    .orElseThrow(() -> new RuntimeException("Grievance not found"));

            // Update the status
            grievance.setStatus(newStatus);
            userGrievanceService.updateGrievanceStatus(complaintId, newStatus); // Call the service to update

            return new ResponseEntity<>("Grievance status updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update grievance status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/grievances")
    public ResponseEntity<List<Grievance>> getGrievancesByAssigneeId(@RequestParam String assigneeId) {
        try {
            // Fetch grievances by assigneeId
            List<Grievance> grievances = userGrievanceService.getGrievancesByAssigneeId(assigneeId);

            if (grievances.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No grievances found for the assignee
            }

            return new ResponseEntity<>(grievances, HttpStatus.OK); // Return the grievances list
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
