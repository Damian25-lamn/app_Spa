package com.neilspa.appointment.logic;

import com.neilspa.appointment.dto.CitaDTO;

import java.util.List;

public interface CitaService {
CitaDTO crearCita(CitaDTO dto);
List<CitaDTO> obtenerCitasPorCliente(String clienteId);
List<CitaDTO> obtenerCitasPorEspecialista(String especialistaId);
CitaDTO cancelarCita(String id);
}
