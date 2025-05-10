package com.MSCliente.Cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.MSCliente.Cliente.model.Cliente;

@Repository
public interface clienteRepository extends JpaRepository<Cliente, Integer>{

    Optional<Cliente> findByCorreoClienteAndContraseña(String correoCliente, String contraseña);
    
} 
