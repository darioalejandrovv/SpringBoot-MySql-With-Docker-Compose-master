package com.example.demo.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
public class CuentaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria (PK)

    @Column(unique = true)
    private String numeroCuenta; // Número de cuenta único
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private boolean estado;
    @ManyToOne
    private ClienteModel cliente; // Relación ManyToOne con Cliente

    // Constructor, getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    // ...
}
