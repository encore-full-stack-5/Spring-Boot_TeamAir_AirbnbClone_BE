package com.air.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ReservationException extends RuntimeException {

    private final ReservationErrorCode reservationErrorCode;

    public ReservationException(ReservationErrorCode reservationErrorCode) {
        this.reservationErrorCode = reservationErrorCode;
    }
}
