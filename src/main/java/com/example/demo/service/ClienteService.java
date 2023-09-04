package com.example.demo.service;

import com.example.demo.model.ClienteModel;
import com.example.demo.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public ClienteModel createCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Otros métodos según necesidades
}
