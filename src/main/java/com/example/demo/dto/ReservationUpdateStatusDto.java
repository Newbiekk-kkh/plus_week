package com.example.demo.dto;

import com.example.demo.enums.ReservationStatus;
import lombok.Getter;

@Getter
public class ReservationUpdateStatusDto {
    private ReservationStatus status;
}
