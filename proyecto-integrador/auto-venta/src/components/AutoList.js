import React, { useState, useEffect } from 'react';
import AutoService from '../services/AutoService';

import './AutoList.css'; 
export const AutoList = () => {
  const [autos, setAutos] = useState([]);

  useEffect(() => {
    listarAutos();
  }, []);

  const listarAutos = () => {
    AutoService.getAllAutos()
      .then(response => setAutos(response.data))
      .catch(error => console.log(error));
  };


  const obtenerImagen = (marca) => {
    switch (marca.toLowerCase()) {
      case 'toyota':
        return '/images/toyota_corolla.jpg';
      case 'honda':
        return '/images/honda_civic.jpg';
      case 'mazda':
        return '/images/Mazda3.png';
      default:
        return '/images/default.jpg'; 
         // Imagen por defecto si no se encuentra la marca
    }
  };







  return (
    <div>
      <h2>Autos Disponibles</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Año</th>
            <th>Imagen</th>
          </tr>
        </thead>
        <tbody>
          {autos.map((auto) => (
            <tr key={auto.id}>
              <td>{auto.id}</td>
              <td>{auto.marca}</td>
              <td>{auto.modelo}</td>
              <td>{auto.year}</td>
              <td>
              <img 
                  src={obtenerImagen(auto.marca)} 
                  alt={auto.marca + ' ' + auto.modelo}
                  className="imagen" 
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AutoList;
