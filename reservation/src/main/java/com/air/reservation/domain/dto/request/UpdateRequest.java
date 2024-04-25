package com.air.reservation.domain.dto.request;

import com.air.reservation.domain.entity.Reservation;

import java.time.LocalDate;

public record UpdateRequest(
        Integer guest_count, String startDate, String endDate,
        String message, Integer total_money, String status
) {
    public Reservation toEntity() {
        return Reservation.builder()
                .guestCount(guest_count)
                .startDate(startDate)
                .endDate(endDate)
                .message(message)
                .total_money(total_money)
                .status(status)
                .build();
    }
}
