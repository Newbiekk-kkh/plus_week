package com.example.demo.domain.reservation.dto;

import com.example.demo.domain.reservation.enums.ReservationStatus;
import lombok.Getter;

@Getter
public class ReservationUpdateStatusDto {
    private ReservationStatus status;
}
