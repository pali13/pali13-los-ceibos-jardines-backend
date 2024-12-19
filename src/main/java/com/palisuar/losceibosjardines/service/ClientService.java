package com.palisuar.losceibosjardines.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Client;
import com.palisuar.losceibosjardines.exceptions.CustomExceptions;
import com.palisuar.losceibosjardines.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        // Verificar si ya existe un cliente con el mismo email
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new CustomExceptions.EmailAlreadyExistsException("El correo ya está registrado");
        }
    
        // Si no existe, guardar el cliente
        return clientRepository.save(client);
    }
    

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public boolean clientByEmail(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }

    public boolean deleteClientById(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
