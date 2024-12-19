package com.palisuar.losceibosjardines.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Pay;
import com.palisuar.losceibosjardines.repository.PayRepository;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;

    public List<Pay> getByClientId(Long clientId) {
        return payRepository.findAllByClientId(clientId);
    }

    public List<Pay> getAllPays() {
        return payRepository.findAll();
    }
    
    public Pay savePay(Pay pay) {
        return payRepository.save(pay);
    }

    public Optional<Pay> getPayById(Long id) {
        return payRepository.findById(id);
    }
}
