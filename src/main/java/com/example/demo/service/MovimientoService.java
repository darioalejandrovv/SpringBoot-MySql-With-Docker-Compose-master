package com.example.demo.service;

import com.example.demo.model.CuentaModel;
import com.example.demo.model.MovimientoModel;
import com.example.demo.repository.ICuentaRepository;
import com.example.demo.repository.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private IMovimientoRepository movimientoRepository;
    @Autowired
    private ICuentaRepository cuentaRepository;

    public List<MovimientoModel> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<MovimientoModel> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    @Transactional
    public MovimientoModel createMovimiento(MovimientoModel movimiento) {
        validateTipoMovimiento(movimiento.getTipoMovimiento()); // Validar el tipo de movimiento
        validateNumeroCuentaExists(movimiento.getNumeroCuenta()); // Validar que la cuenta exista
        CuentaModel cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getNumeroCuenta()); //obtengo la cuenta
        validateSaldoDisponible(movimiento.getTipoMovimiento(), cuenta.getSaldoInicial()); //validar que saldo inicial no sea cero

        movimiento.setSaldoInicial(cuenta.getSaldoInicial()); //seteo saldo inicial en movimiento desde DB de cuenta
        movimiento.setSaldoDisponible(generateSaldoDisponible(movimiento.getTipoMovimiento(),movimiento.getValor(),movimiento.getSaldoInicial())); //actualizar saldo disponible
        cuenta.setSaldoInicial(movimiento.getSaldoDisponible()); //actualizar nuevo saldo a la cuenta
        movimientoRepository.save(movimiento);
        cuentaRepository.save(cuenta);
        return movimiento;
    }

    private BigDecimal generateSaldoDisponible(String tipoMovimiento, BigDecimal movimiento, BigDecimal saldoInicial){
        if (tipoMovimiento.equals("Retiro")) return saldoInicial.subtract(movimiento);
        else if (tipoMovimiento.equals("Depósito")) return saldoInicial.add(movimiento);
        else return BigDecimal.valueOf(0);
    }

    private void validateTipoMovimiento(String tipoMovimiento) {
        if (!tipoMovimiento.equalsIgnoreCase("Retiro") && !tipoMovimiento.equalsIgnoreCase("Depósito")) {
            throw new IllegalArgumentException("Error de tipo de Movimiento, especifique un tipo de movimiento sea 'Retiro' o 'Depósito'");
        }
    }
    private void validateSaldoDisponible (String tipoMovimiento, BigDecimal saldoInicial) {
        if (tipoMovimiento.equalsIgnoreCase("Retiro") && saldoInicial.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Saldo no disponible.");
        }
    }
    private void validateNumeroCuentaExists(String numeroCuenta) {
        Optional<CuentaModel> cuentaOptional = Optional.ofNullable(cuentaRepository.findByNumeroCuenta(numeroCuenta));
        if  (cuentaOptional.isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta especificado no existe");
        }
    }

    @Transactional
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    // Otros métodos según necesidades
}
