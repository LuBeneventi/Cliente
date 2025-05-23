package com.MSCliente.Cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MSCliente.Cliente.model.Cliente;
import com.MSCliente.Cliente.model.perfilCliente;
import com.MSCliente.Cliente.repository.clienteRepository;

@Service
public class clienteService {
    
    @Autowired
    private clienteRepository clienteRepository;

    public Cliente Registrarse(Cliente cliente) {
        Optional<Cliente> existente = clienteRepository.existsByCorreo(cliente.getCorreo());
        if(existente != null){
            new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> iniciarSesion(String correo, String contraseña) {
        return clienteRepository.findByCorreoAndContraseña(correo, contraseña);
    }

    public Optional<Cliente> BuscarCorreo(String correo){
        return clienteRepository.existsByCorreo(correo);
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

    public boolean buscarID(int idCliente) {
        return clienteRepository.existsById(idCliente);
    }

    public Optional<Cliente> buscar(int idCliente){
        return clienteRepository.findByidCliente(idCliente);
    }

}
