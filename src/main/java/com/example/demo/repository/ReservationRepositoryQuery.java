package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepositoryQuery {
    List<Reservation> searchReservationsWithDynamicQuery(Long userId, Long itemId);
}
