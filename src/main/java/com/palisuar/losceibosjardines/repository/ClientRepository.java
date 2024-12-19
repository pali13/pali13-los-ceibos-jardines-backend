package com.palisuar.losceibosjardines.repository;

import java.util.Optional;

import com.palisuar.losceibosjardines.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
