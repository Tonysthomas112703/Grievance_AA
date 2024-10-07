// src/App.jsx
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import UserDashboard from './components/UserDashboard'; 
import SupervisorDashboard from './components/SupervisorDashboard';// Import your components
import AssigneeDashboard from './components/AssigneeDashboard';
import { AuthProvider } from './context/AuthContext';
import Home from './components/Home'; // Import the Home component if you create one

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} /> {/* Define a route for the home page */}
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/userDashboard" element={<UserDashboard />} />
          <Route path="/SupervisorDashboard" element={<SupervisorDashboard />} />
          <Route path="/assignee-dashboard" element={<AssigneeDashboard />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
