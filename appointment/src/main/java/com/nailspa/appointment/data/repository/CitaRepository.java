package com.nailspa.appointment.data.repository;

import com.nailspa.appointment.data.entity.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitaRepository extends MongoRepository<Cita, String> {
List<Cita> findByClienteId(String clienteId);
List<Cita> findByEspecialistaId(String especialistaId);
}
