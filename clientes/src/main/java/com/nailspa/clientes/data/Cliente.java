package com.nailspa.clientes.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "clientes") // Nombre de la colección en MongoDB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private String id;  // Mongo usa String para el id (ObjectId)

    private String nombre;
    private String correo;
    private String telefono;
    private String cedula;
}

