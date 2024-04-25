package com.air.reservation.domain.dto.request;

import com.air.reservation.domain.entity.Reservation;

import java.time.LocalDate;

public record UpdateRequest(
        Integer guest_count, String check_in, String check_out,
        String message, Integer total_money
) {
    public Reservation toEntity(LocalDate startDate, LocalDate endDate) {
        return Reservation.builder()
                .guestCount(guest_count)
                .startDate(startDate)
                .endDate(endDate)
                .message(message)
                .total_money(total_money)
                .build();
    }
}
