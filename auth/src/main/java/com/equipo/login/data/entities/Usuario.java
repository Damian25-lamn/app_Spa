package com.equipo.login.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String id; 
    
    @Email(message = "Correo electrónico inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String username;
   
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;  // Importante: guardar la contraseña hasheada
    private String rol;       // Ej: ADMIN, USER
 // Ej: ADMIN, USER
}
