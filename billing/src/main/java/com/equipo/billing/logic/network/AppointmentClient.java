package com.equipo.billing.logic.network;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.equipo.billing.logic.data.network.AppointmentEP;

@FeignClient(name = "appointment-service", url = "http://localhost:8081")
public interface AppointmentClient {
    @GetMapping("/buscarReservaPorCliente")
    List<AppointmentEP> getID(@RequestParam("cliente") String idCliente);
}