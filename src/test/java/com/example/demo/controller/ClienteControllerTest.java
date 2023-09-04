package com.example.demo.controller;

import com.example.demo.controller.ClienteController;
import com.example.demo.model.ClienteModel;
import com.example.demo.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClientes() {
        // Datos de prueba
        List<ClienteModel> clientesMock = new ArrayList<>();
        clientesMock.add(new ClienteModel());
        clientesMock.add(new ClienteModel());

        // Configuración de mocks
        when(clienteService.getAllClientes()).thenReturn(clientesMock);

        // Ejecutar la prueba
        List<ClienteModel> response = clienteController.getAllClientes();

        // Verificar resultados
        assertEquals(clientesMock.size(), response.size());

        // Verificar que el servicio se llamó una vez
        verify(clienteService, times(1)).getAllClientes();
    }

    @Test
    public void testGetClienteById() {
        // Datos de prueba
        Long clienteId = 1L;
        ClienteModel clienteMock = new ClienteModel();
        clienteMock.setId(clienteId);

        // Configuración de mocks
        when(clienteService.getClienteById(clienteId)).thenReturn(Optional.of(clienteMock));

        // Ejecutar la prueba
        ResponseEntity<ClienteModel> response = clienteController.getClienteById(clienteId);

        // Verificar resultados
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteMock, response.getBody());

        // Verificar que el servicio se llamó una vez
        verify(clienteService, times(1)).getClienteById(clienteId);
    }

    // Puedes agregar más pruebas para otros métodos según sea necesario
}
