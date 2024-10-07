import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('');
    const [message, setMessage] = useState('');

    const handleRegister = async (e) => {
        e.preventDefault();
        
        console.log("Submitting Registration:", { userName, password, role });
    
        try {
            const response = await axios.post('http://localhost:8080/api/register', {
                username: userName, // Send as 'username' to match the DTO
                password,
                role,
            });
    
            setMessage("User Registered Successfully"); // Assuming this is a string on success
        } catch (error) {
            if (error.response) {
                if (typeof error.response.data === 'object' && error.response.data.message) {
                    setMessage(error.response.data.message); // Set only the message
                } else {
                    setMessage('An unexpected error occurred. Please try again.');
                }
            } else {
                setMessage('An unexpected error occurred. Please try again.');
            }
        }
    };
    

    return (
        <div className="container">
            <h2>Register</h2>
            <form onSubmit={handleRegister}>
                <div className="form-group">
                    <label htmlFor="userName">Username:</label>
                    <input
                        type="text"
                        className="form-control"
                        id="userName"
                        value={userName}
                        onChange={(e) => setUserName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="role">Role:</label>
                    <select
                        className="form-control"
                        id="role"
                        value={role}
                        onChange={(e) => setRole(e.target.value)}
                        required
                    >
                        <option value="">Select Role</option>
                        <option value="USER">User</option>
                        <option value="ASSIGNEE">Assignee</option>
                        <option value="SUPERVISOR">Supervisor</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Register</button>
            </form>
            {message && <div className="alert alert-info mt-3">{message}</div>}
        </div>
    );
};

export default Register;
