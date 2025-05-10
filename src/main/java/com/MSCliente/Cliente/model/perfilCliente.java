package com.MSCliente.Cliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class perfilCliente {
    private String nomCliente;
    private String apCliente;
    private String correo;
    private String dirCliente;
}
