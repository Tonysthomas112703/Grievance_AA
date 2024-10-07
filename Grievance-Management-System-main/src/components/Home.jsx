// src/components/Home.jsx
import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
            <h1>Welcome to the Grievance Management System</h1>
            <p>Please choose an option below:</p>
            <Link to="/login" style={{ margin: '10px', fontSize: '18px' }}>
                Login/Register
            </Link>
            
        </div>
    );
};

export default Home;
