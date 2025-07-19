package com.equuipo.billing.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document(collection = "billings")
public class BillingDB {
    @Id
    private String id;
    private String idAppointment; // ID de cita
    private Double amount;
    private String paymentMethod; // Método de pago (tarjeta, efectivo, etc.)
    private long reservationDate;
}
