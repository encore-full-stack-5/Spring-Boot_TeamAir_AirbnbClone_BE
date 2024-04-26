package com.air.reservation.controller;

import com.air.reservation.domain.dto.request.CreateDateRequest;
import com.air.reservation.domain.dto.request.CreateRequest;
import com.air.reservation.domain.dto.request.UpdateRequest;
import com.air.reservation.domain.dto.response.ReadResponse;
import com.air.reservation.domain.entity.Reservation;
import com.air.reservation.domain.entity.ReservationDate;
import com.air.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/create/{id}")
    public void createReservation(@RequestBody CreateRequest req,
                                  @PathVariable("id") Long roomId,
                                  @RequestParam("check_in") String startDate,
                                  @RequestParam("check_out") String endDate) {
        reservationService.create(req, roomId, startDate, endDate);
    }

    @PostMapping("/date/create/{id}")
    public List<ReservationDate> createReservationDate(@RequestBody CreateDateRequest req, @PathVariable("id") Long roomId) {
        return reservationService.createReservationDate(req, roomId);
    }

    @GetMapping("/user/reservation/{id}")
    public List<ReadResponse> getByReservationToUser(@PathVariable("id") Long userId) {
        return reservationService.getByReservationToUser(userId);
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
