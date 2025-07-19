package com.neilspa.appointment.controller;

import com.neilspa.appointment.dto.CitaDTO;
import com.neilspa.appointment.logic.CitaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    @Autowired
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
