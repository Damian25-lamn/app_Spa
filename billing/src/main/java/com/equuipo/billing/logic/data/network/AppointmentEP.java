package com.equuipo.billing.logic.data.network;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentEP {
    //esta clase solo de prueba, despues que cambiar con los atributos de la clase Cita
    public String id;
    public String customerId;
    public int tableNumber;
    public Date reservationDate;
    public String notes;
    public String status;
}
