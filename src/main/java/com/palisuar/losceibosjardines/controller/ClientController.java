package com.palisuar.losceibosjardines.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.entity.Client;
import com.palisuar.losceibosjardines.service.ClientService;

@RestController
@RequestMapping("/api/client/")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
