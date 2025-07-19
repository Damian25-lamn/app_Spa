package com.equipo.citas.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    private String id;  // Mongo usa String como ObjectId

    private String clienteId;      // ID del cliente
    private String especialistaId; // ID del especialista (staff)
    private String fechaHora;      // Podrías usar LocalDateTime + @JsonFormat si prefieres
    private String estado;         // Ej: programada, completada, cancelada
}
