package com.example.demo.controller;

import com.example.demo.model.ClienteModel;
import com.example.demo.model.CuentaModel;
import com.example.demo.model.MovimientoModel;
import com.example.demo.model.ReporteModel;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.ICuentaRepository;
import com.example.demo.repository.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// ...

@RestController
public class ReporteController {

    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Autowired
    private ICuentaRepository cuentaRepository;

    @Autowired
    private IClienteRepository clienteRepository; // Agrega el repositorio del cliente

    //@GetMapping("/reportes?inicio={fechaInicio}&fin={fechaFin}&id={clienteId}")
    @GetMapping("/reportes")
    public ResponseEntity<List<ReporteModel>> generarReporte(

            @RequestParam String clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ){
        //busqueda de cliente por clienteId en método personalizado en el repositorio
        ClienteModel cliente = clienteRepository.findByClienteId(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe.");// Manejar el caso si el cliente no existe
        }
        //crear Lista de Objetos de respuesta
        List<ReporteModel> reportes = new ArrayList<>();
        //obtener cuentas de cliente
        List<CuentaModel> cuentas = cuentaRepository.findByClienteClienteId(cliente.getClienteId());
        for (CuentaModel itemCuenta : cuentas) {
            List<MovimientoModel> movimientos = movimientoRepository.findByFechaBetweenAndNumeroCuenta(fechaInicio.atStartOfDay(), fechaFin.atTime(23, 59, 59), itemCuenta.getNumeroCuenta());
            for (MovimientoModel itemMovimiento: movimientos){
                ReporteModel itemReporte = new ReporteModel(); //crear objeto
                itemReporte.setFecha(itemMovimiento.getFecha().toLocalDate());
                itemReporte.setCliente(cliente.getNombre());
                itemReporte.setNumeroCuenta(itemCuenta.getNumeroCuenta());
                itemReporte.setTipo(itemCuenta.getTipoCuenta());
                itemReporte.setSaldoInicial(itemMovimiento.getSaldoInicial());
                itemReporte.setEstado(itemCuenta.isEstado());
                //Signo si es Retiro o Depósito
                BigDecimal valorSigno = BigDecimal.valueOf((itemMovimiento.getTipoMovimiento().equals("Retiro"))?-1:1);
                itemReporte.setMovimiento(itemMovimiento.getValor().multiply(valorSigno));
                itemReporte.setSaldoDisponible(itemMovimiento.getSaldoDisponible());
                reportes.add(itemReporte);
            }
        }
        return ResponseEntity.ok(reportes);
    }
}
