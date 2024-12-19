package com.palisuar.losceibosjardines.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.palisuar.losceibosjardines.dto.ParkDTO;
import com.palisuar.losceibosjardines.dto.PayDTO;
import com.palisuar.losceibosjardines.entity.Park;
import com.palisuar.losceibosjardines.exceptions.CustomExceptions;
import com.palisuar.losceibosjardines.service.ParkService;

@RestController
@RequestMapping("/api/park/")
public class ParkController {
    @Autowired
    private ParkService parkService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<ParkDTO> getAllParks() {
        return parkService.getAll().stream()
                .map(park -> new ParkDTO(
                        park.getId(),
                        park.getName(),
                        park.getPays().stream()
                                .map(pay -> new PayDTO(
                                        pay.getId(),
                                        pay.getAmount(),
                                        pay.getPayDate(),
                                        pay.getStatus(),
                                        pay.getClient().getName(),
                                        pay.getPark().getName()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("client/{clientId}")
    public List<Park> getByClientId(@PathVariable Long clientId) {
        return parkService.getByClientId(clientId);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public ParkDTO getParkById(@PathVariable Long id) {
        Park park = parkService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Park not found"));

        return new ParkDTO(
                park.getId(),
                park.getName(),
                park.getPays().stream()
                        .map(pay -> new PayDTO(
                                pay.getId(),
                                pay.getAmount(),
                                pay.getPayDate(),
                                pay.getStatus(),
                                pay.getClient().getName(),
                                pay.getPark().getName()))
                        .collect(Collectors.toList()));
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping
    public ResponseEntity<?> createOrUpdatePark(@RequestBody Park park) {
        try {
            Park savedClient = parkService.savePark(park);
            return ResponseEntity.ok(savedClient);
        } catch (CustomExceptions.EmailAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
