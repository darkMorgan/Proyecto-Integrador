// Login.js
import React, { useState } from 'react';
import './Login.css'; // Si aún quieres usar CSS adicional

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Nombre de usuario:', username);
    console.log('Contraseña:', password);
    // Aquí puedes agregar la lógica para autenticar al usuario
  };

  // Definir el estilo en línea para el fondo
  const backgroundStyle = {
    backgroundImage: 'url(/images/motormarket.jpg)', // Ruta relativa en public
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    height: '110vh', // 100% de la altura de la ventana
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'flex-start', // Cambiar a 'flex-start' para acercar el formulario a la parte superior
    paddingTop: '0px',
  };

  return (
    <div style={backgroundStyle}>
      <div className="login-form">
        <h2>Iniciar Sesión</h2>
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label htmlFor="username">Nombre de Usuario</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Ingresa tu usuario"
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">Contraseña</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Ingresa tu contraseña"
            />
          </div>
          <button type="submit" className="login-btn">Iniciar Sesión</button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
