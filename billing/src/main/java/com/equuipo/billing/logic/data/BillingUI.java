package com.equuipo.billing.logic.data;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BillingUI {
    private String id;
    private String idAppointment; // ID de cita
    private Double amount;
    private String paymentMethod; // Método de pago (tarjeta, efectivo, etc.)
    private LocalDateTime reservationDate;
}
