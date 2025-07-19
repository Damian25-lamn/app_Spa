package com.nailspa.clientes.controller;

import com.nailspa.clientes.data.Cliente;
import com.nailspa.clientes.logic.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<Cliente> obtenerPorCedula(@PathVariable String cedula) {
        return clienteService.buscarPorCedula(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

