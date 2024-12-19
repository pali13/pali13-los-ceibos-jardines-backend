package com.palisuar.losceibosjardines.repository;

import com.palisuar.losceibosjardines.entity.Park;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {
    Optional<Park> findByName(String name);
    public List<Park> findByClientId(Long clientId);
}
