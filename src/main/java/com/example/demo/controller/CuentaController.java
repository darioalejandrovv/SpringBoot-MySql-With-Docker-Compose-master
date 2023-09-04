package com.example.demo.controller;

import com.example.demo.model.CuentaModel;
import com.example.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<CuentaModel> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaModel> getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuentaModel> createCuenta(@RequestBody CuentaModel cuenta) {
        CuentaModel createdCuenta = cuentaService.createCuenta(cuenta);
        return ResponseEntity.ok(createdCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }

    // Otros métodos según necesidades
}
