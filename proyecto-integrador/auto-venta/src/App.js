import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AutoList from './components/AutoList';
import AutoDetail from './components/AutoDetail';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';


import RegisterPage from './pages/RegisterPage';


import { Link } from 'react-router-dom';






function App() {
  return (
    <Router>
      <div>
        
        
        {/* Navbar con Bootstrap */}
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
          <div className="container">
            <Link className="navbar-brand" to="/">MotorMarket</Link>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <Link className="nav-link active" to="/">Inicio</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/autos">Autos Disponibles</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">Iniciar Sesión</Link>
                </li>
                <li className='nav-items'>
                  <Link className="nav-link" to="/register">Regístrate</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/autos" element={<AutoList />} />
          <Route path="/autos/:id" element={<AutoDetail />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>

        



      </div>
    </Router>
  );
}

export default App;
