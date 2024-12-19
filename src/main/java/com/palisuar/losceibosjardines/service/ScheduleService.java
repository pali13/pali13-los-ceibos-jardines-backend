package com.palisuar.losceibosjardines.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Schedule;
import com.palisuar.losceibosjardines.repository.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Optional<Schedule> getById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }
    
}
