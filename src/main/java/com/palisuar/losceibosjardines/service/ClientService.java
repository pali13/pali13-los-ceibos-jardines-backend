package com.palisuar.losceibosjardines.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Client;
import com.palisuar.losceibosjardines.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }
}
