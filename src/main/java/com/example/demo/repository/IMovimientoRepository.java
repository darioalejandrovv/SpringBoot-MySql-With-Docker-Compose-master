package com.example.demo.repository;

import com.example.demo.model.MovimientoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<MovimientoModel, Long> {

    @Query("SELECT m FROM MovimientoModel m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin AND m.numeroCuenta = :numeroCuenta")
    List<MovimientoModel> findByFechaBetweenAndNumeroCuenta(
            LocalDateTime fechaInicio, LocalDateTime fechaFin, String numeroCuenta);
}
