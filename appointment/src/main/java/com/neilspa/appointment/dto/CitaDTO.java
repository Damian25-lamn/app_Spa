package com.neilspa.appointment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CitaDTO {

    private String id;

    @NotNull
    private String clienteId;

    @NotNull
    private String especialistaId;

    @NotNull
    private String servicioId;

    @NotNull
    private Double precio;

    @NotNull
    private LocalDateTime fechaHora;

}
