package com.MSCliente.Cliente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column(length = 50, nullable = false)
    private String nomCliente;

    @Column(length = 50, nullable = false)
    private String apCliente;

    @Column(length = 250, nullable = false, unique = true)
    private String correo;

    @Column(length = 50, nullable = false)
    private String contraseña;

    @Column(length = 100, nullable = false)
    private String dirCliente;

    public perfilCliente verPerfil() {
        return new perfilCliente(nomCliente, apCliente, correo, dirCliente);
    }
}
