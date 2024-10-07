import React, { useState } from 'react';
import axios from 'axios';
import './Styles/AssigneeDashboard.css';

const AssigneeDashboard = () => {
    const [grievances, setGrievances] = useState([]);
    const [assigneeId, setAssigneeId] = useState('');
    const [selectedGrievanceId, setSelectedGrievanceId] = useState('');                    
    const [newStatus, setNewStatus] = useState('');
    const [message, setMessage] = useState('');

    // Function to handle fetching grievances
    const fetchGrievances = async () => {
        if (!assigneeId) {
            setMessage('Please enter Assignee ID');
            return;
        }
        try {
            const response = await axios.get(`http://localhost:8080/api/assignee/grievances`, {
                params: { assigneeId: assigneeId },
            });

            if (response.data.length === 0) {
                setMessage('No grievances found for this Assignee ID.');
                setGrievances([]); // Clear previous grievances if no data found
            } else {
                setGrievances(response.data);
                setMessage(''); // Clear the message if grievances are found
            }
        } catch (error) {
            console.error('Error fetching grievances:', error);
            setMessage('Failed to fetch grievances.');
        }
    };

    // Function to handle updating grievance status
    const updateGrievanceStatus = async (e) => {
      e.preventDefault();
      if (!selectedGrievanceId || !newStatus) {
          setMessage('Please select a grievance and enter a new status.');
          return;
      }
  
      try {
          // Make the API call to update the grievance status
          const response = await axios.put(`http://localhost:8080/api/assignee/updateStatus`, null, {
              params: {
                  complaintId: selectedGrievanceId,
                  newStatus: newStatus,
              },
          });
  
          // Check if the response indicates success
          if (response.status === 200) {
              setMessage('Grievance status updated successfuly!');
          } else {
              setMessage('Failed to update grievance status.');
          }
  
          // Refresh grievances after status update
          fetchGrievances(); 
          setNewStatus(''); // Clear the new status input
          // Don't clear selected grievance ID here
      } catch (error) {
          console.error('Error updating grievance status:', error);
          setMessage('Failed to update grievance status.');
      }
  };

    // Reset grievances and clear message when Assignee ID changes
    const handleAssigneeIdChange = (e) => {
        setAssigneeId(e.target.value);
        setGrievances([]); // Clear grievances when assigneeId is changed
        setSelectedGrievanceId(''); // Clear selected grievance ID
        setMessage(''); // Clear message when changing Assignee ID
    };

    // Function to handle grievance selection
    const handleSelectGrievance = (id) => {
        setSelectedGrievanceId(id);
        // Don't clear the success message when a grievance is selected
    };

    return (
        <div className="assignee-dashboard">
            <h2>Assignee Dashboard</h2>

            {message && <div className="alert alert-info">{message}</div>}

            <div className="assignee-id-section">
                <label htmlFor="assigneeIdInput">Enter Assignee ID:</label>
                <input
                    type="text"
                    id="assigneeIdInput"
                    className="form-control"
                    value={assigneeId}
                    onChange={handleAssigneeIdChange}
                />
                <button onClick={fetchGrievances} className="btn btn-primary">Fetch Grievances</button>
            </div>

            {grievances.length > 0 && (
                <div className="grievance-list-section">
                    <h3>Grievances</h3>
                    <ul className="grievance-list">
                        {grievances.map((grievance) => (
                            <li key={grievance.complaint_id} className="grievance-item">
                                <h4>Complaint ID: {grievance.complaint_id}</h4>
                                <p>Complaint: {grievance.complaint}</p>
                                <p>Description: {grievance.description}</p>
                                <p>Status: {grievance.status}</p>
                                <button
                                    onClick={() => handleSelectGrievance(grievance.complaint_id)}
                                    className="btn btn-secondary"
                                >
                                    Select Grievance
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            )}

            {grievances.length > 0 && (
                <div className="update-status-section">
                    <h3>Update Grievance Status</h3>
                    <form onSubmit={updateGrievanceStatus} className="update-status-form">
                        <div className="form-group">
                            <label htmlFor="grievanceIdInput">Selected Grievance ID:</label>
                            <input
                                type="text"
                                id="grievanceIdInput"
                                className="form-control"
                                value={selectedGrievanceId}
                                readOnly
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="statusInput">Enter New Status:</label>
                            <input
                                type="text"
                                id="statusInput"
                                className="form-control"
                                value={newStatus}
                                onChange={(e) => setNewStatus(e.target.value)}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">Update Status</button>
                    </form>
                </div>
            )}
        </div>
    );
};

export default AssigneeDashboard;
