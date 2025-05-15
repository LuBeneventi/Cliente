package com.MSCliente.Cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cliente> registrarse(@RequestBody Cliente cliente) {
        if(cliente.getIdCliente() != 0 && clienteService.buscarID(cliente.getIdCliente())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clienteService.Registrarse(cliente), HttpStatus.OK);
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
    public ResponseEntity<Void> eliminarCuenta(@PathVariable int id) {
        clienteService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<Cliente> editarDatos(@RequestBody Cliente cliente, @PathVariable int id) {
        cliente.setIdCliente(id);
        return ResponseEntity.ok(clienteService.editarDatos(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable int id){
        return clienteService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
