package com.nailspa.especialistas.logic;

import org.springframework.stereotype.Component;

@Component
public class TechnicianLogic {

    public void registrarServicio(String staffId, String servicio) {
        System.out.println("Staff #" + staffId + " realizó servicio: " + servicio);
    }
}
