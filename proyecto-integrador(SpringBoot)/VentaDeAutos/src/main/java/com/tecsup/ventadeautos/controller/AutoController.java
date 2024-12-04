package com.tecsup.ventadeautos.controller;

import com.tecsup.ventadeautos.excepciones.ResourceNotFoundException;
import com.tecsup.ventadeautos.model.Auto;
import com.tecsup.ventadeautos.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")  // Permite solicitudes de un origen específico
@RestController
@RequestMapping("/api/v1")  // Punto de entrada para la API de Autos
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;  // Inyectamos el repositorio de Auto


    private final String UPLOAD_DIR = "uploads/";



    // Obtener todos los autos
    @GetMapping("/autos")
    public List<Auto> listarAutos() {
        System.out.println("Consultando los autos...");
        return autoRepository.findAll();
    }
    // Guardar un nuevo auto
    @PostMapping("/autos")
    public Auto guardarAuto(@RequestBody Auto auto) {
        return autoRepository.save(auto);  // Guardamos el auto en la base de datos
    }




    // Actualizar un auto por su id
    @PutMapping("/autos/{id}")
    public ResponseEntity<Auto> actualizarAuto(@PathVariable Long id, @RequestBody Auto autoRequest) {
        // Buscar el auto por id y lanzar excepción si no lo encuentra
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El auto no existe con el id: " + id));

        // Actualizar los campos del auto con los datos de autoRequest
        auto.setMarca(autoRequest.getMarca());
        auto.setModelo(autoRequest.getModelo());
        auto.setYear(autoRequest.getYear());

        // Guardar el auto actualizado en la base de datos
        Auto autoActualizado = autoRepository.save(auto);
        return ResponseEntity.ok(autoActualizado);  // Devolver el auto actualizado
    }

    // Eliminar un auto por su id
    @DeleteMapping("/autos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarAuto(@PathVariable Long id) {
        // Buscar el auto por id y lanzar excepción si no lo encuentra
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El auto no existe con el id: " + id));

        // Eliminar el auto de la base de datos
        autoRepository.delete(auto);

        // Responder con un mensaje de confirmación
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);  // Indicamos que fue eliminado
        return ResponseEntity.ok(response);
    }
}
