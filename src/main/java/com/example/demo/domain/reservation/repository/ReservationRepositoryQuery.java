package com.example.demo.domain.reservation.repository;

import com.example.demo.domain.reservation.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepositoryQuery {
    List<Reservation> searchReservationsWithDynamicQuery(Long userId, Long itemId);
}
