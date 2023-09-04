package com.example.demo.repository;

import com.example.demo.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Long> {
    // Métodos personalizados si los necesitas
    ClienteModel findByClienteId(String clienteId);
}

