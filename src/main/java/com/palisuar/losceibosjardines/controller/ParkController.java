package com.palisuar.losceibosjardines.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.entity.Park;
import com.palisuar.losceibosjardines.service.ParkService;

@RestController
@RequestMapping("/api/park/")
public class ParkController {
    @Autowired
    private ParkService parkService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public ResponseEntity<Park> getApplianceById(@PathVariable Long id) {
        Optional<Park> park = parkService.getById(id);
        return park.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
