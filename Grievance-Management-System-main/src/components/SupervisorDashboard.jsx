// src/SupervisorDashboard.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Styles/SupervisorDashboard.css'; // Import your CSS file

const SupervisorDashboard = () => {
    const [grievances, setGrievances] = useState([]);
    const [technicians, setTechnicians] = useState([]);
    const [selectedComplaintId, setSelectedComplaintId] = useState(''); // updated from grievanceId
    const [selectedAssigneeId, setSelectedAssigneeId] = useState('');
    const [inputComplaintId, setInputComplaintId] = useState(''); // New input for manual complaint ID
    const [message, setMessage] = useState('');

    // Fetch grievances
    const fetchGrievances = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/supervisor/grievances');
            setGrievances(response.data);
        } catch (error) {
            console.error('Error fetching grievances:', error);
            setMessage('Failed to fetch grievances.');
        }
    };

    // Fetch technicians
    // Fetch technicians
const fetchTechnicians = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/supervisor/technicians');
        console.log(response.data); // Log the response to see the structure of the data
        setTechnicians(response.data);
    } catch (error) {
        console.error('Error fetching technicians:', error);
        setMessage('Failed to fetch technicians.');
    }
};


    // Assign Assignee to Grievance
    const handleAssignAssignee = async (e) => {
        e.preventDefault();
        try {
            const complaintIdToAssign = inputComplaintId || selectedComplaintId; // Use the input complaint ID if provided
            if (!complaintIdToAssign) {
                setMessage('Please select or input a complaint ID.');
                return;
            }

            await axios.put('http://localhost:8080/api/supervisor/assign', null, {
                params: {
                    complaintId: complaintIdToAssign, // updated to complaintId
                    assigneeId: selectedAssigneeId,
                },
            });
            setMessage('Assignee assigned to complaint successfully!');
            fetchGrievances(); // Refresh grievances after assignment
        } catch (error) {
            console.error('Error assigning assignee:', error);
            setMessage('Failed to assign assignee.');
        }
    };

    useEffect(() => {
        fetchGrievances();
    }, []);

    return (
        <div className="supervisor-dashboard">
            <h2>Supervisor Dashboard</h2>

            {message && <div className="alert alert-info">{message}</div>}

            <h3>Grievances</h3>
            <ul className="grievance-list">
                {grievances.map((grievance) => (
                    <li key={grievance.complaint_id} className="grievance-item">
                        <h4>Complaint ID: {grievance.complaint_id}</h4>
                        <p>Description: {grievance.description}</p>
                        <p>Complaint: {grievance.complaint} | Status: {grievance.status}</p>
                    </li>
                ))}
            </ul>

            <h3>Technicians</h3>
            <button onClick={fetchTechnicians} className="btn btn-secondary">Fetch Technicians</button>
            <ul className="technician-list">
                {technicians.length > 0 && technicians.map((tech) => (
                    <li key={tech.id} className="technician-item">
                        {tech.username} (ID: {tech.id})
                    </li>
                ))}
            </ul>

            <h3>Assign Assignee to Complaint</h3>
            <form onSubmit={handleAssignAssignee} className="assign-form">
                <div className="form-group">
                    <label htmlFor="complaintIdInput">Enter Complaint ID:</label>
                    <input
                        type="text"
                        id="complaintIdInput"
                        className="form-control"
                        placeholder="Complaint ID"
                        value={inputComplaintId}
                        onChange={(e) => setInputComplaintId(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="assignee">Select Assignee:</label>
                    <select
    id="assignee"
    className="form-control"
    value={selectedAssigneeId}
    onChange={(e) => setSelectedAssigneeId(e.target.value)}
    required
>
    <option value="">Select Assignee</option>
    {technicians.map((tech) => (
        <option key={tech.id} value={tech.id}>
            {tech.username}  {/* Display username, but the value is user_id */}
        </option>
    ))}
</select>

                </div>
                <button type="submit" className="btn btn-primary">Assign</button>
            </form>
        </div>
    );
};

export default SupervisorDashboard;
