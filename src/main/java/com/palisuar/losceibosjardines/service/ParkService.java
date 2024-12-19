package com.palisuar.losceibosjardines.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palisuar.losceibosjardines.entity.Park;
import com.palisuar.losceibosjardines.exceptions.CustomExceptions;
import com.palisuar.losceibosjardines.repository.ParkRepository;

@Service
public class ParkService {
    @Autowired
    private ParkRepository parkRepository;

    public Optional<Park> getById(Long parkId) {
        return parkRepository.findById(parkId);
    }

    public List<Park> getAll() {
        return parkRepository.findAll();
    }

    public List<Park> getByClientId(Long clientId){
        return parkRepository.findByClientId(clientId);
    }

    public Park savePark(Park park) {
        if (park.getId() == null) {
            // Verificar si ya existe un parque con el mismo nombre
            if (parkRepository.findByName(park.getName()).isPresent()) {
                throw new CustomExceptions.NameAlreadyExistsException("El nombre ya está registrado");
            }
        }
        // Si no existe, guardar el parque
        return parkRepository.save(park);
    }
}
