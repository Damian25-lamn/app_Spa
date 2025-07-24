package com.nailspa.appointment.logic;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nailspa.appointment.data.entity.Cita;
import com.nailspa.appointment.data.repository.CitaRepository;
import com.nailspa.appointment.dto.CitaDTO;
import com.nailspa.appointment.logic.network.BillingClient;
import com.nailspa.appointment.logic.network.dto.BillingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repo;
    private final BillingClient billingClient;

    @Override
    public CitaDTO crearCita(CitaDTO dto) {
        Cita cita = Cita.builder()
                .clienteId(dto.getClienteId())
                .especialistaId(dto.getEspecialistaId())
                .servicioId(dto.getServicioId())
                .fechaHora(dto.getFechaHora())
                .estado("PENDIENTE")
                .precio(dto.getPrecio())
                .build();

        Cita citaGuardada = repo.save(cita);

        // Llamar al microservicio de facturación usando GET
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String fechaFormateada = citaGuardada.getFechaHora().format(formatter);

        try {
            BillingResponse factura = billingClient.emitirFactura(
                    citaGuardada.getId(),
                    citaGuardada.getPrecio(),
                    "efectivo", // método de pago por defecto
                    fechaFormateada);
            System.out.println("Factura creada con ID: " + factura.getId());
        } catch (Exception e) {
            System.out.println("Error al crear la factura: " + e.getMessage());
        }

        return toDTO(citaGuardada);
    }

    @Override
    public List<CitaDTO> obtenerCitasPorCliente(String clienteId) {
        return repo.findByClienteId(clienteId).stream().map(this::toDTO).toList();
    }

    @Override
    public List<CitaDTO> obtenerCitasPorEspecialista(String especialistaId) {
        return repo.findByEspecialistaId(especialistaId).stream().map(this::toDTO).toList();
    }

    @Override
    public CitaDTO cancelarCita(String id) {
        Cita cita = repo.findById(id).orElseThrow();
        cita.setEstado("CANCELADA");
        return toDTO(repo.save(cita));
    }

    private CitaDTO toDTO(Cita c) {
        return CitaDTO.builder()
                .id(c.getId())
                .clienteId(c.getClienteId())
                .especialistaId(c.getEspecialistaId())
                .servicioId(c.getServicioId())
                .fechaHora(c.getFechaHora())
                .estado(c.getEstado())
                .precio(c.getPrecio())
                .build();
    }

    @Override
    public CitaDTO obtenerCitaPorId(String id) {
        return repo.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

}
