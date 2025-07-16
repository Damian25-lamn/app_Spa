package com.equipo.billing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.equipo.billing.logic.BillingUC;
import com.equipo.billing.logic.data.BillingUI;
import feign.FeignException;

@RestController
public class BillingController {
    @Autowired
    private BillingUC billingService;

    /**
     * Endpoint para emitir una factura.
     *
     * @param idCita        ID de la cita relacionada con la factura.
     * @param amount        Monto a cobrar.
     * @param paymentMethod Método de pago utilizado (por ejemplo, efectivo, tarjeta).
     * @param reservationDate Fecha y hora de la cita (formato ISO: yyyy-MM-dd'T'HH:mm:ss).
     * @return Mensaje indicando el resultado de la emisión de la factura.
     *
     * <p>
     * <strong>Endpoint:</strong> {@code GET /emitirfactura}
     * </p>
     * <p>
     * <strong>Ejemplo:</strong>
     * </p>
     * <pre>
     * GET /emitirfactura?idCita=123&amp;monto=25.0&amp;metodoPago=efectivo&amp;fecha=2025-07-10T14:30:00
     * </pre>
     */
    @GetMapping("/emitirfactura")
    public org.springframework.http.ResponseEntity<?> issueInvoice(
            @RequestParam("idCita") String idCita,
            @RequestParam("monto") Double amount,
            @RequestParam("metodoPago") String paymentMethod,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservationDate) {
        try {
            BillingUI billing = billingService.insertBilling(idCita, amount, paymentMethod, reservationDate);
            String message = "Factura emitida correctamente: " + billing.getId();

            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Parámetros inválidos: " + e.getMessage());
        } catch (FeignException.NotFound e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe reserva para la cita " + idCita + ": " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al emitir la factura: " + e.getMessage());
        }
    }

    @GetMapping("/buscarTodasFacturas")
    public List<BillingUI> findAll() {
        try {
            return billingService.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al obtener las facturas: " + e.getMessage());
        }
    }

}
