package com.example.demo.service;

import com.example.demo.model.CuentaModel;
import com.example.demo.repository.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private ICuentaRepository cuentaRepository;

    public List<CuentaModel> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<CuentaModel> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Transactional
    public CuentaModel createCuenta(CuentaModel cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    // Otros métodos según necesidades
}