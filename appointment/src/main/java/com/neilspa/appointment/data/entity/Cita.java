package com.neilspa.appointment.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Document(collection = "citas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Cita {

@Id
private String id;

private String clienteId;
private String especialistaId;
private String servicioId;
private LocalDateTime fechaHora;
private String estado;
private double precio;

}
