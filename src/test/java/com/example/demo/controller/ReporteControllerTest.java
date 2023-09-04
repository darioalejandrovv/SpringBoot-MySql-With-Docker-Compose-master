package com.example.demo.controller;

import com.example.demo.controller.ReporteController;
import com.example.demo.model.ClienteModel;
import com.example.demo.model.CuentaModel;
import com.example.demo.model.MovimientoModel;
import com.example.demo.model.ReporteModel;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.ICuentaRepository;
import com.example.demo.repository.IMovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReporteControllerTest {

    @InjectMocks
    private ReporteController reporteController;

    @Mock
    private IMovimientoRepository movimientoRepository;

    @Mock
    private ICuentaRepository cuentaRepository;

    @Mock
    private IClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerarReporte() {
        // Datos de prueba
        String clienteId = "cliente123";
        LocalDate fechaInicio = LocalDate.of(2023, 1, 15);
        LocalDate fechaFin = LocalDate.of(2023, 1, 31);

        // Cliente mock
        ClienteModel clienteMock = new ClienteModel();
        clienteMock.setClienteId(clienteId);
        clienteMock.setNombre("Cliente de Prueba");

        // Cuenta mock
        CuentaModel cuentaMock = new CuentaModel();
        cuentaMock.setNumeroCuenta("cuenta123");
        cuentaMock.setTipoCuenta("Cuenta de Prueba");
        cuentaMock.setSaldoInicial(new BigDecimal("1000.00"));
        cuentaMock.setEstado(true);

        // Movimiento mock
        MovimientoModel movimientoMock = new MovimientoModel();
        movimientoMock.setFecha(LocalDateTime.of(2023, 1, 15, 12, 0, 0));
        movimientoMock.setTipoMovimiento("Depósito");
        movimientoMock.setValor(new BigDecimal("500.00"));
        movimientoMock.setSaldoInicial(new BigDecimal("1000.00"));
        movimientoMock.setSaldoDisponible(new BigDecimal("1500.00"));
        movimientoMock.setNumeroCuenta("cuenta123");

        // Lista de cuentas mock
        List<CuentaModel> cuentasMock = new ArrayList<>();
        cuentasMock.add(cuentaMock);

        // Lista de movimientos mock
        List<MovimientoModel> movimientosMock = new ArrayList<>();
        movimientosMock.add(movimientoMock);

        // Configuración de mocks
        when(clienteRepository.findByClienteId(clienteId)).thenReturn(clienteMock);
        when(cuentaRepository.findByClienteClienteId(clienteId)).thenReturn(cuentasMock);
        when(movimientoRepository.findByFechaBetweenAndNumeroCuenta(
                fechaInicio.atStartOfDay(), fechaFin.atTime(23, 59, 59), cuentaMock.getNumeroCuenta()))
                .thenReturn(movimientosMock);

        // Ejecutar la prueba
        ResponseEntity<List<ReporteModel>> response = reporteController.generarReporte(clienteId, fechaInicio, fechaFin);
        List<ReporteModel> reporteList = response.getBody();

        // Verificar resultados
        assertEquals(1, reporteList.size());
        ReporteModel reporte = reporteList.get(0);
        assertEquals(fechaInicio, reporte.getFecha());
        assertEquals(clienteMock.getNombre(), reporte.getCliente());
        assertEquals(cuentaMock.getNumeroCuenta(), reporte.getNumeroCuenta());
        assertEquals(cuentaMock.getTipoCuenta(), reporte.getTipo());
        assertEquals(movimientoMock.getSaldoInicial(), reporte.getSaldoInicial());
        assertEquals(cuentaMock.isEstado(), reporte.isEstado());
        assertEquals(movimientoMock.getValor(), reporte.getMovimiento());
        assertEquals(movimientoMock.getSaldoDisponible(), reporte.getSaldoDisponible());

        // Verificar que los métodos de los repositorios se llamen correctamente
        verify(clienteRepository, times(1)).findByClienteId(clienteId);
        verify(cuentaRepository, times(1)).findByClienteClienteId(clienteId);
        verify(movimientoRepository, times(1)).findByFechaBetweenAndNumeroCuenta(
                fechaInicio.atStartOfDay(), fechaFin.atTime(23, 59, 59), cuentaMock.getNumeroCuenta());
    }
}
