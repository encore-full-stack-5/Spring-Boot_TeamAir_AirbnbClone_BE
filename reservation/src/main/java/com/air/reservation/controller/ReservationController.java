package com.air.reservation.controller;

import com.air.reservation.domain.dto.request.CreateDateRequest;
import com.air.reservation.domain.dto.request.CreateRequest;
import com.air.reservation.domain.dto.request.UpdateRequest;
import com.air.reservation.domain.dto.response.ReadResponse;
import com.air.reservation.domain.entity.ReservationDate;
import com.air.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public String messageAccess() {
        return "fuck you";
    }

    @PostMapping("/create")
    public void createReservation(@RequestBody CreateRequest req) {
        reservationService.create(req);
    }

    @PostMapping("/date/create")
    public List<ReservationDate> createReservationDate(@RequestBody CreateDateRequest req) {
        return reservationService.createReservationDate(req);
    }

    @GetMapping("/{id}")
    public ReadResponse getOneReservation(@PathVariable("id") Long id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/update/{id}")
    public void updateReservation(@PathVariable("id") Long id, @RequestBody UpdateRequest req) {
        reservationService.update(id, req);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable("id") Long id) {
        reservationService.delete(id);
    }
}
