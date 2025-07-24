package com.nailspa.appointment.controller;

import com.nailspa.appointment.dto.CitaDTO;
import com.nailspa.appointment.logic.CitaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping
    public CitaDTO crear(@Valid @RequestBody CitaDTO dto) {
        return citaService.crearCita(dto);
    }

    @GetMapping("/cliente/{id}")
    public List<CitaDTO> citasCliente(@PathVariable String id) {
        return citaService.obtenerCitasPorCliente(id);
    }

    @GetMapping("/especialista/{id}")
    public List<CitaDTO> citasEspecialista(@PathVariable String id) {
        return citaService.obtenerCitasPorEspecialista(id);
    }

    @PutMapping("/cancelar/{id}")
    public CitaDTO cancelar(@PathVariable String id) {
        return citaService.cancelarCita(id);
    }

}

// Endpoint separado para billing (fuera del mapping /api/citas)
@RestController
@RequiredArgsConstructor
class BillingIntegrationController {

    private final CitaService citaService;

    // Endpoint para que billing pueda buscar reservas por cliente
    @GetMapping("/buscarReservaPorCliente")
    public List<CitaDTO> buscarReservaPorCliente(@RequestParam("cliente") String clienteId) {
        return citaService.obtenerCitasPorCliente(clienteId);
    }
}
