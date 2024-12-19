package com.palisuar.losceibosjardines.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.entity.Client;
import com.palisuar.losceibosjardines.exceptions.CustomExceptions;
import com.palisuar.losceibosjardines.service.ClientService;

@RestController
@RequestMapping("/api/client/")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() ->
        ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client savedClient = clientService.saveClient(client);
            return ResponseEntity.ok(savedClient);
        } catch (CustomExceptions.EmailAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        boolean isDeleted = clientService.deleteClientById(id);

        if (isDeleted) {
            // Si la eliminación es exitosa, retornamos una respuesta 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // Si no se encuentra el recurso, retornamos un 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
