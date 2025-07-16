package com.nailspa.especialistas.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    private String id; // Mongo usa String como ID (ObjectId)

    private String nombre;
    private String especialidad;
    private String telefono;
    private String horarioTrabajo;
    private String rol;
}
