package com.equuipo.billing.logic;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equuipo.billing.data.entities.BillingDB;
import com.equuipo.billing.data.repositories.BillingRepository;
import com.equuipo.billing.logic.data.BillingUI;
import com.equuipo.billing.logic.data.network.AppointmentEP;
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
        List<AppointmentEP> appointmentID = appointmentClient.getID(idAppointment);
        if (appointmentID == null || appointmentID.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la cita con ID: " + idAppointment);
        }
        try {
            var billing = BillingDB.builder()
                    .idAppointment(appointmentID.get(0).getCustomerId())
                    .amount(amount)
                    .paymentMethod(paymentMethod)
                    .reservationDate(reservationDate.toInstant(java.time.ZoneOffset.UTC).toEpochMilli())
                    .build();

            return convertToUI(billingRepository.save(billing));
        } catch (FeignException ex) {
            if (ex.status() == 404) {
                throw new IllegalArgumentException("Cita " + idAppointment + " no encontrada.");
            }
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Error al insertar la reserva: " + ex.getMessage(), ex);
        }
    }

    public BillingUI findByIdAppointment(String idAppointment) {
        var billing = billingRepository.findByIdAppointment(idAppointment);
        if (billing == null) {
            throw new IllegalArgumentException("No se encontró facturación para el ID de cita: " + idAppointment);
        }
        return convertToUI(billing);
    }

    public List<BillingUI> findAll() {
        return billingRepository.findAll().stream()
                .map(this::convertToUI)
                .toList();
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

}
