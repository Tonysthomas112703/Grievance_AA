package com.example.grievance.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "grievance")
public class Grievance {

    @Id
    @Column(name = "complaint_id")
    private String complaint_id; // Alphanumeric and auto-generated

    @Column(name = "consumer_Name")
    private String consumerName; // Should start with 100

    @Column(name = "complaint_type")
    private String complaint;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private String status; // Default: 'submitted'

    @Column(name = "AssigneeId") // Column name in DB remains as 'AssigneeId'
    private String assigneeId; // Java field uses camelCase

    // Constructor
    public Grievance() {
        this.status = "submitted"; // Sets the default status as 'submitted'
    }

    // Method to generate alphanumeric complaint_id
    @PrePersist
    private void prePersist() {
        if (this.complaint_id == null) {
            this.complaint_id = generateAlphanumericId(); // Generates a random alphanumeric complaint_id
        }
    }

    private String generateAlphanumericId() {
        return "CMP" + UUID.randomUUID().toString().replace("-", "").substring(0, 8); // Generates a random alphanumeric complaint_id
    }

    // Getters and Setters

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    public void getDate(LocalDateTime now) {
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }
}
