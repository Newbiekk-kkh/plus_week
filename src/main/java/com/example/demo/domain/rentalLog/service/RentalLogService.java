package com.example.demo.domain.rentalLog.service;

import com.example.demo.domain.rentalLog.entity.RentalLog;
import com.example.demo.domain.rentalLog.repository.RentalLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RentalLogService {
    private final RentalLogRepository rentalLogRepository;

    public RentalLogService(RentalLogRepository rentalLogRepository) {
        this.rentalLogRepository = rentalLogRepository;
    }

    @Transactional
    public void save(RentalLog rentalLog) {
        rentalLogRepository.save(rentalLog);
    }
}
