package com.example.demo.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clientes") // Nombre de la tabla en la base de datos
public class ClienteModel extends PersonaModel {
    @Column(unique = true)
    private String clienteId; // Clave única para el cliente (PK)
    private String contrasena;
    private boolean estado;


    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    //private List<CuentaModel> cuentas = new ArrayList<>(); // Relación OneToMany con Cuenta


    // Constructor, getters y setters

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    // ...
}
