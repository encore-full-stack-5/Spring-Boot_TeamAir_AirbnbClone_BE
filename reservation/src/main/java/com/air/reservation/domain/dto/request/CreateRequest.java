package com.air.reservation.domain.dto.request;

import com.air.reservation.domain.entity.Reservation;

import java.time.LocalDate;

public record CreateRequest(
        Integer guest_count, String check_in, String check_out,
        boolean reserve_status, String message,
        Integer total_money
) {
    // check_in, check_out 타입 지정 필요
    public Reservation toEntity(LocalDate check_in, LocalDate check_out) {
        return new Reservation(
                null,
                guest_count,
                check_in,
                check_out,
                reserve_status,
                message,
                total_money,
                null
        );
    }
}
