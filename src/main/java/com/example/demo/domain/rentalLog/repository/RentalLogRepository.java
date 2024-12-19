package com.example.demo.domain.rentalLog.repository;

import com.example.demo.domain.rentalLog.entity.RentalLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalLogRepository extends JpaRepository<RentalLog, Long> {
}
