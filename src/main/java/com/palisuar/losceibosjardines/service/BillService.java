package com.palisuar.losceibosjardines.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Bill;
import com.palisuar.losceibosjardines.repository.BillRepository;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getClientById(Long clientId) {
        return billRepository.findAllByClientId(clientId);
    }
}
