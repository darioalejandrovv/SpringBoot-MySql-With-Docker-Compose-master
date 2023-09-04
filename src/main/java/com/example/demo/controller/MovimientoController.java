package com.example.demo.controller;

import com.example.demo.model.MovimientoModel;
import com.example.demo.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<MovimientoModel> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoModel> getMovimientoById(@PathVariable Long id) {
        return movimientoService.getMovimientoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovimientoModel> createMovimiento(@RequestBody MovimientoModel movimiento) {
        MovimientoModel createdMovimiento = movimientoService.createMovimiento(movimiento);
        return ResponseEntity.ok(createdMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    // Otros métodos según necesidades
}
