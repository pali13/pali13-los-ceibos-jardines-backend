package com.palisuar.losceibosjardines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palisuar.losceibosjardines.entity.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    public List<Pay> findAllByClientId(Long clientId);
    
}
