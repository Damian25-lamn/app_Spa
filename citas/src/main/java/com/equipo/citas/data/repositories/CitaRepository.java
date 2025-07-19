package com.equipo.citas.data.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.equipo.citas.data.entities.Cita;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    List<Cita> findByClienteId(String clienteId);

    List<Cita> findByEspecialistaId(String especialistaId);
}
