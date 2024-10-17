package com.palisuar.losceibosjardines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palisuar.losceibosjardines.entity.Bill;

@Repository
public interface  BillRepository extends JpaRepository<Bill, Long> {

    public List<Bill> findAllByClientId(Long clientId);
    
}
