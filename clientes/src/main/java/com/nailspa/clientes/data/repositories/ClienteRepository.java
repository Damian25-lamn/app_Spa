package com.nailspa.clientes.data.repositories;

import com.nailspa.clientes.data.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByCedula(String cedula);
}
