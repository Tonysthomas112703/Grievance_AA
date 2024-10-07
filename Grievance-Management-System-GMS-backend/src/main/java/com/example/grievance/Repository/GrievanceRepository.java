package com.example.grievance.Repository;

import com.example.grievance.Entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrievanceRepository extends JpaRepository<Grievance, String> {
    // Custom query to find grievances by consumer name
    List<Grievance> findByConsumerName(String consumerName);
    List<Grievance> findByAssigneeId(String assigneeId);

}
