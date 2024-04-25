package com.air.reservation.service;

import com.air.reservation.domain.dto.request.CreateDateRequest;
import com.air.reservation.domain.dto.request.CreateRequest;
import com.air.reservation.domain.dto.request.UpdateRequest;
import com.air.reservation.domain.dto.response.ReadResponse;
import com.air.reservation.domain.entity.Reservation;
import com.air.reservation.domain.entity.ReservationDate;
import com.air.reservation.domain.repository.ReservationDateRepository;
import com.air.reservation.domain.repository.ReservationRepository;
import com.air.reservation.exception.ReservationErrorCode;
import com.air.reservation.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private  final ReservationDateRepository reservationDateRepository;

    @Override
    public void create(CreateRequest req) {
        LocalDate startDate = LocalDate.parse(req.check_in());
        LocalDate endDate = LocalDate.parse(req.check_out());
        reservationRepository.save(req.toEntity(startDate, endDate));
    }

    @Override
    public List<ReservationDate> createReservationDate(CreateDateRequest req) {
        String[] startDate = req.checkIn().split("-");
        String[] endDate = req.checkOut().split("-");
        List<ReservationDate> exist = reservationDateRepository.findByDateRange(startDate[2], endDate[2]);
        if(!exist.isEmpty() && startDate[1].equals(exist.get(0).getMonth()) &&endDate[0].equals(exist.get(0).getMonth()))
            throw new IllegalArgumentException();
        List<ReservationDate> reservationDateList= addDate(req.checkIn(), req.checkOut());
        return reservationDateRepository.saveAll(reservationDateList);
    }

    private List<ReservationDate> addDate(String checkIn, String checkOut) {
        LocalDate start = LocalDate.parse(checkIn);
        LocalDate end = LocalDate.parse(checkOut);
        long dayDiff = ChronoUnit.DAYS.between(start, end);
        List<ReservationDate> reservationDates = new ArrayList<>();
        for (int i = 0; i <= dayDiff; i++) {
            String[] date = start.plusDays(i).toString().split("-");
            ReservationDate reservationDate = ReservationDate.builder()
                    .year(date[0])
                    .month(date[1])
                    .day(date[2])
                    .build();
            reservationDates.add(reservationDate);
        }
        return reservationDates;
    }

    @Override
    public ReadResponse getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVE_NOT_FOUND));
        return new ReadResponse(reservation.getId(),
                reservation.getGuestCount(),
                reservation.getStartDate(), reservation.getEndDate(),
                reservation.getMessage(), reservation.getTotal_money());
    }

    @Override
    public void update(Long id, UpdateRequest req) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVE_NOT_FOUND));
        reservation.setGuestCount(req.guest_count());
        reservation.setStartDate(LocalDate.parse(req.check_in()));
        reservation.setEndDate(LocalDate.parse(req.check_out()));
        reservation.setMessage(req.message());
        reservation.setTotal_money(req.total_money());
        reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVE_NOT_FOUND));
        reservationRepository.delete(reservation);
    }
}
