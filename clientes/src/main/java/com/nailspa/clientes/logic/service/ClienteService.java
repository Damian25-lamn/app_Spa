package com.nailspa.clientes.logic.service;



import com.nailspa.clientes.data.Cliente;
import com.nailspa.clientes.data.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula);
    }

    public void eliminarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
