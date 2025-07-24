package com.equuipo.billing.logic;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equuipo.billing.data.dto.CitaDTO;
import com.equuipo.billing.data.entities.BillingDB;
import com.equuipo.billing.data.repositories.BillingRepository;
import com.equuipo.billing.logic.data.BillingUI;
import com.equuipo.billing.logic.network.AppointmentClient;

import feign.FeignException;

@Service
public class BillingUC {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private AppointmentClient appointmentClient;

    public BillingUI insertBilling(String idAppointment, Double amount, String paymentMethod,
            LocalDateTime reservationDate) {

        try {
            // Por ahora, creamos la factura sin validar la cita específica
            // TODO: Implementar validación cuando el endpoint de citas por ID funcione

            // Crear factura
            var billing = BillingDB.builder()
                    .idAppointment(idAppointment)
                    .amount(amount)
                    .paymentMethod(paymentMethod)
                    .reservationDate(reservationDate.toInstant(java.time.ZoneOffset.UTC).toEpochMilli())
                    .build();

            return convertToUI(billingRepository.save(billing));

        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar la factura: " + ex.getMessage(), ex);
        }
    }

    public BillingUI convertToUI(BillingDB billing) {
        return BillingUI.builder()
                .id(billing.getId())
                .idAppointment(billing.getIdAppointment())
                .amount(billing.getAmount())
                .paymentMethod(billing.getPaymentMethod())
                .reservationDate(LocalDateTime.ofInstant(
                        java.time.Instant.ofEpochMilli(billing.getReservationDate()),
                        java.time.ZoneId.systemDefault()))
                .build();
    }

    public List<BillingUI> findAll() {
        return billingRepository.findAll().stream()
                .map(this::convertToUI)
                .toList();
    }

}
