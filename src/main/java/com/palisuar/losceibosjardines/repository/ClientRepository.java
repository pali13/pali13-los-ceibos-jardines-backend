package com.palisuar.losceibosjardines.repository;

import com.palisuar.losceibosjardines.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
