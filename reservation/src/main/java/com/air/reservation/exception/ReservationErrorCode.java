package com.air.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ReservationErrorCode {
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED),
    RESERVE_NOT_FOUND(HttpStatus.NOT_FOUND),
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT),;

    private final HttpStatus httpStatus;
}
