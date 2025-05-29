package com.MSCliente.Cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try{
            return new ResponseEntity<>(clienteService.Registrarse(cliente), HttpStatus.OK);
        }
        catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/perfil")
    public perfilCliente verPerfil(@PathVariable int id) {
        return clienteService.verPerfil(id);
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<Cliente> activarCliente(@PathVariable int id){
        try{
            return ResponseEntity.ok(clienteService.activaCliente(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}/desactivar")
    public ResponseEntity<Cliente> desactivarCliente(@PathVariable int id){
        try{
            return ResponseEntity.ok(clienteService.desactivarCliente(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
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
