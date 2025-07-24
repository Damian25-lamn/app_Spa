package com.nailspa.appointment.logic;

import com.nailspa.appointment.dto.CitaDTO;

import java.util.List;

public interface CitaService {
    CitaDTO crearCita(CitaDTO dto);

    List<CitaDTO> obtenerCitasPorCliente(String clienteId);

    List<CitaDTO> obtenerCitasPorEspecialista(String especialistaId);

    CitaDTO cancelarCita(String id);

    CitaDTO obtenerCitaPorId(String id);
}
