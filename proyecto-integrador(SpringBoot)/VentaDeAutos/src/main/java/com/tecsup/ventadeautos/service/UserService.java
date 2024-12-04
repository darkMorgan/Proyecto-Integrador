package com.tecsup.ventadeautos.service;



import com.tecsup.ventadeautos.model.User;
import com.tecsup.ventadeautos.repository.UserRepository;
import com.tecsup.ventadeautos.excepciones.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password, String email) {
        // Validar si el usuario ya existe
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Crear el nuevo usuario
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // En un caso real, deberías hashear la contraseña
        newUser.setEmail(email);

        return userRepository.save(newUser);
    }
}

