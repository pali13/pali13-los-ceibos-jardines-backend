package com.palisuar.losceibosjardines.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palisuar.losceibosjardines.entity.Pay;
import com.palisuar.losceibosjardines.service.PayService;

@RestController
@RequestMapping("/api/client/{clientId}/pays")
public class PayController {
    @Autowired
    private PayService payService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<Pay> getPaysByClientId(@PathVariable Long clientId) {
        return payService.getByClientId(clientId);
    }
}
