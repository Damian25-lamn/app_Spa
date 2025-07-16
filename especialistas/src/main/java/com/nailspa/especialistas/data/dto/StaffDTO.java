package com.nailspa.especialistas.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDTO {
    private String id;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String horarioTrabajo;
    private String rol;
}

