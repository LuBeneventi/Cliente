package com.MSCliente.Cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MSCliente.Cliente.model.Cliente;
import com.MSCliente.Cliente.model.perfilCliente;
import com.MSCliente.Cliente.service.clienteService;

@RestController
@RequestMapping("api/cliente")
public class clienteController {
    @Autowired
    private clienteService clienteService;

    @PostMapping("/registro")
    public Cliente registrarse(@RequestBody Cliente cliente) {
        return clienteService.Registrarse(cliente);
    }

    @PostMapping("/login")
    public Optional<Cliente> iniciarSesion(@RequestParam String correo, @RequestParam String contraseña) {
        return clienteService.iniciarSesion(correo, contraseña);
    }

    @GetMapping("/{id}/perfil")
    public perfilCliente verPerfil(@PathVariable int id) {
        return clienteService.verPerfil(id);
    }

    @DeleteMapping("/{id}/eliminar")
    public void eliminarCuenta(@PathVariable int id) {
        clienteService.eliminarCuenta(id);
    }

    @PutMapping("/{id}/editar")
    public Cliente editarDatos(@RequestBody Cliente cliente, @PathVariable int id) {
        cliente.setIdCliente(id);
        return clienteService.editarDatos(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
}
