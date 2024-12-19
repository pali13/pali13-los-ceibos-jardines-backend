package com.palisuar.losceibosjardines.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.entity.Bill;
import com.palisuar.losceibosjardines.service.BillService;


@RestController
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private BillService billService;

    // @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<Bill> getClientById(@PathVariable Long clientId) {
        return billService.getClientById(clientId);
    }
}
