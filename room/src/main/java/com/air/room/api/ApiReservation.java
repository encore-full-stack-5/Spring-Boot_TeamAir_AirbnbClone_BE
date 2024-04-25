package com.air.room.api;

import com.air.room.dto.request.CreateDateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiReservation {
    private final FeignReservation feignReservation;

    @Async
    public List<ReservationDate> createReservationDate(CreateDateRequest req, Long rid) {
        try{
            feignReservation.createReservationDate(req, rid);
        }catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
