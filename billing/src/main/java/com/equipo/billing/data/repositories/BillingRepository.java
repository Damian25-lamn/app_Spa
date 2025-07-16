package com.equipo.billing.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.equipo.billing.data.entities.BillingDB;

public interface BillingRepository extends MongoRepository<BillingDB, String> {
    
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, para buscar facturas por ID de cita:
    @Query("{ 'idAppointment': ?0 }")
    BillingDB findByIdAppointment(String idAppointment);

}
