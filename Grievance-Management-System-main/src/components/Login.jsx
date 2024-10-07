// src/Login.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate instead of useHistory
import axios from 'axios';
import './Styles/Login.css'; // Make sure to import your CSS file

const Login = () => {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState(''); // Role state
    const [message, setMessage] = useState('');
    const navigate = useNavigate(); // Use useNavigate for navigation

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/login', null, {
                params: {
                    userName,
                    password,
                    role,
                },
            });

            // Handle successful login
            setMessage(response.data);
            console.log(response.data); // Display the response message

            // Redirect based on role
            switch (role) {
                case 'USER':
                    navigate('/userDashboard'); // Navigate to User Dashboard
                    break;
                case 'ASSIGNEE':
                    navigate('/assignee-dashboard'); // Navigate to Assignee Dashboard
                    break;
                case 'SUPERVISOR':
                    navigate('/SupervisorDashboard'); // Navigate to Technician Dashboard (if you have it)
                    break;
                default:
                    setMessage('Invalid role specified.'); // Handle invalid role
            }
        } catch (error) {
            if (error.response) {
                // Handle error response from server
                setMessage(error.response.data);
            } else {
                // Handle other errors (e.g., network issues)
                setMessage('An unexpected error occurred. Please try again.');
            }
        }
    };

    const handleRegister = () => {
        navigate('/register'); // Navigate to the registration page
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
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
                        <option value="">Select a role</option>
                        <option value="USER">User</option>
                        <option value="ASSIGNEE">Assignee</option>
                        <option value="SUPERVISOR">Supervisor</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Login</button>
                <button type="button" className="btn btn-secondary ml-2" onClick={handleRegister}>
                    Register
                </button>
            </form>
            {message && <div className="alert alert-info mt-3">{message}</div>}
        </div>
    );
};

export default Login;
