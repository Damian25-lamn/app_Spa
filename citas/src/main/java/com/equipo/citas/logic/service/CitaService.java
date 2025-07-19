package com.equipo.citas.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo.citas.data.entities.Cita;
import com.equipo.citas.data.repositories.CitaRepository;
import com.equipo.citas.logic.service.*;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerPorId(String id) {
        return citaRepository.findById(id);
    }

    public void eliminarCita(String id) {
        citaRepository.deleteById(id);
    }

    public List<Cita> listarPorCliente(String clienteId) {
        return citaRepository.findByClienteId(clienteId);
    }

    public List<Cita> listarPorEspecialista(String especialistaId) {
        return citaRepository.findByEspecialistaId(especialistaId);
    }
}
