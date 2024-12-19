package com.palisuar.losceibosjardines.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.entity.Schedule;
import com.palisuar.losceibosjardines.service.ScheduleService;


@RestController
@RequestMapping("/api/schedule/")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> park = scheduleService.getById(id);
        return park.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
