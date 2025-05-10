package com.MSCliente.Cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MSCliente.Cliente.model.Cliente;
import com.MSCliente.Cliente.model.perfilCliente;
import com.MSCliente.Cliente.repository.clienteRepository;

@Service
public class clienteService {
    @Autowired
    private clienteRepository clienteRepository;

    public Cliente Registrarse(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> iniciarSesion(String correo, String contraseña) {
        return clienteRepository.findByCorreoClienteAndContraseña(correo, contraseña);
    }

    public perfilCliente verPerfil(int idCliente) {
        return clienteRepository.findById(idCliente)
                .map(Cliente::verPerfil)
                .orElse(null);
    }

    public void eliminarCuenta(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    public Cliente editarDatos(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
   
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

}
