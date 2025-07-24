package com.equuipo.billing.logic.network;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.equuipo.billing.data.dto.CitaDTO;

@FeignClient(name = "appointment-service", url = "http://localhost:8081") // Ajusta puerto si es otro
public interface AppointmentClient {

    @GetMapping("/api/citas/{id}")
    CitaDTO obtenerCitaPorId(@PathVariable("id") String id);
}