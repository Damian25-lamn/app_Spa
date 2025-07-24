package com.nailspa.appointment.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitaDTO {
    private String id;
    private String clienteId;
    private String especialistaId;
    private String servicioId;
    private LocalDateTime fechaHora;
    private String estado;
    private double precio;
}
