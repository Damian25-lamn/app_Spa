package com.nailspa.especialistas.logic;

import org.springframework.stereotype.Service;

import com.nailspa.especialistas.data.dto.StaffDTO;
import com.nailspa.especialistas.data.entity.Staff;
import com.nailspa.especialistas.data.repository.StaffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final AdminLogic adminLogic;
    private final TechnicianLogic technicianLogic;

    public StaffService(StaffRepository staffRepository,
                        AdminLogic adminLogic,
                        TechnicianLogic technicianLogic) {
        this.staffRepository = staffRepository;
        this.adminLogic = adminLogic;
        this.technicianLogic = technicianLogic;
    }

    public List<StaffDTO> obtenerTodos() {
        return staffRepository.findAll().stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    public Optional<StaffDTO> obtenerPorId(String id) {
        return staffRepository.findById(id).map(this::convertirA_DTO);
    }

    public List<StaffDTO> obtenerPorRol(String rol) {
        return staffRepository.findByRol(rol).stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    public StaffDTO guardar(StaffDTO dto) {
        Staff guardado = staffRepository.save(convertirA_Entidad(dto));
        return convertirA_DTO(guardado);
    }

    public void eliminar(String id) {
        staffRepository.deleteById(id);
    }

    private StaffDTO convertirA_DTO(Staff staff) {
        return StaffDTO.builder()
                .id(staff.getId())
                .nombre(staff.getNombre())
                .especialidad(staff.getEspecialidad())
                .telefono(staff.getTelefono())
                .horarioTrabajo(staff.getHorarioTrabajo())
                .rol(staff.getRol())
                .build();
    }

    private Staff convertirA_Entidad(StaffDTO dto) {
        return new Staff(
                dto.getId(),
                dto.getNombre(),
                dto.getEspecialidad(),
                dto.getTelefono(),
                dto.getHorarioTrabajo(),
                dto.getRol()
        );
    }
}
