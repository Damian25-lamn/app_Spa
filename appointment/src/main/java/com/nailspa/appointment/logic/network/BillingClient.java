package com.nailspa.appointment.logic.network;

import com.nailspa.appointment.logic.network.dto.BillingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "billing-service", url = "http://localhost:8084")
public interface BillingClient {

    @GetMapping(value = "/emitirfactura", produces = "application/json")
    BillingResponse emitirFactura(
            @RequestParam("idCita") String idCita,
            @RequestParam("monto") Double amount,
            @RequestParam("metodoPago") String paymentMethod,
            @RequestParam("fecha") String reservationDateIso);

    @GetMapping("/facturas/{id}")
    BillingResponse obtenerFacturaPorId(@PathVariable String id);
}
