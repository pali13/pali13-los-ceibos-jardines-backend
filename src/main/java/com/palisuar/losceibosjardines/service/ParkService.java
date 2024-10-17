package com.palisuar.losceibosjardines.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Park;
import com.palisuar.losceibosjardines.repository.ParkRepository;

@Service
public class ParkService {
    @Autowired
    private ParkRepository parkRepository;

    public Optional<Park> getById(Long parkId) {
        return parkRepository.findById(parkId);
    }
}
