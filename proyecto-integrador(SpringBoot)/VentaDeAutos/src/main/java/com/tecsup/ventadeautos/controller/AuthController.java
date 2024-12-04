package com.tecsup.ventadeautos.controller;

import com.tecsup.ventadeautos.model.User;
import com.tecsup.ventadeautos.repository.UserRepository;
import com.tecsup.ventadeautos.service.UserService;
import com.tecsup.ventadeautos.excepciones.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")  // Permite solicitudes de un origen específico
@RestController
@RequestMapping("/api/v1")  // Punto de entrada para la API de Usuarios
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping("/usuarios")
    public List<User> listarUsers() {
        System.out.println("Consultando los usuarios...");
        return userRepository.findAll();
    }

    // Crear un nuevo usuario
    @PostMapping("/usuarios")
    public ResponseEntity<User> guardarUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Obtener un usuario por su ID
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> obtenerUserPorId(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    // Actualizar un usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> actualizarUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());

            // Guardamos el usuario actualizado
            userRepository.save(existingUser);

            return ResponseEntity.ok(existingUser);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);

            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
}
