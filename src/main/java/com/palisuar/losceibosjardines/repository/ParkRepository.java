package com.palisuar.losceibosjardines.repository;

import com.palisuar.losceibosjardines.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {
    
}
