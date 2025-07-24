package com.equipo.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.equipo.login.logic.service.UsuarioService;

import jakarta.validation.Valid;

import com.equipo.login.data.entities.Usuario;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para registrar un nuevo usuario
        @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(usuarioService.registrar(usuario));
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Optional<Usuario> encontrado = usuarioService.buscarPorUsername(usuario.getUsername());

        if (encontrado.isPresent() &&
            new BCryptPasswordEncoder().matches(usuario.getPassword(), encontrado.get().getPassword())) {
            return ResponseEntity.ok("Login exitoso!!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas, por favor intente de nuevo.");
        }
    }

}
