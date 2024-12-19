package com.palisuar.losceibosjardines.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.dto.PayDTO;
import com.palisuar.losceibosjardines.dto.PayStatusUpdateDTO;
import com.palisuar.losceibosjardines.entity.Client;
import com.palisuar.losceibosjardines.entity.Park;
import com.palisuar.losceibosjardines.entity.Pay;
import com.palisuar.losceibosjardines.service.ClientService;
import com.palisuar.losceibosjardines.service.ParkService;
import com.palisuar.losceibosjardines.service.PayService;

@RestController
@RequestMapping("/api/pay/")
public class PayController {
    @Autowired
    private PayService payService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ParkService parkService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<PayDTO> getAllPays() {
        return payService.getAllPays().stream()
                .map(pay -> new PayDTO(
                        pay.getId(),
                        pay.getAmount(),
                        pay.getPayDate(),
                        pay.getStatus(),
                        pay.getClient().getName(),
                        pay.getPark().getName()))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping
    public ResponseEntity<?> createPay(@RequestBody Pay pay) {
        try {
            if (clientService.getClientById(pay.getClient().getId()).isPresent()
                    && parkService.getById(pay.getPark().getId()).isPresent()) {
                Client client = clientService.getClientById(pay.getClient().getId()).get();
                Park park = parkService.getById(pay.getPark().getId()).get();
                Pay savedPay = payService.savePay(pay);
                savedPay.setClient(client);
                savedPay.setPark(park);
                return ResponseEntity.ok(savedPay);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePay(@PathVariable Long id, @RequestBody PayStatusUpdateDTO updatePayStatus) {
        try {
            // Buscar el pago existente
            Optional<Pay> optionalPay = payService.getPayById(id);
            if (optionalPay.isPresent()) {
                Pay existingPay = optionalPay.get();

                existingPay.setStatus(updatePayStatus.getStatus());

                // Guardar los cambios
                Pay savedPay = payService.savePay(existingPay);
                return ResponseEntity.ok(savedPay);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pago no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pago.");
        }
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public List<Pay> getPaysByClientId(@PathVariable Long clientId) {
        return payService.getByClientId(clientId);
    }

}
