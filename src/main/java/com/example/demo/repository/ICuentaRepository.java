package com.example.demo.repository;

import com.example.demo.model.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICuentaRepository extends JpaRepository<CuentaModel, Long> {
    CuentaModel findByNumeroCuenta(String numeroCuenta);
    List<CuentaModel> findByClienteClienteId(String clienteId);
}
