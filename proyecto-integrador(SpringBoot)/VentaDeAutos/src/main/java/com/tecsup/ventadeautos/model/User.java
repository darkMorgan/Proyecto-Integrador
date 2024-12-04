package com.tecsup.ventadeautos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")  // Nombre de la tabla en la base de datos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // ID único del usuario

    @Column(name = "username", unique = true, nullable = false)
    private String username;  // Nombre de usuario único

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;  // Contraseña del usuario (sin cifrar por ahora)
}
