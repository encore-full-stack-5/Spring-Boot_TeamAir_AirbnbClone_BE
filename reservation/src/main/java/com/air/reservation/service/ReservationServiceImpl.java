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
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ReservationDateRepository reservationDateRepository;

    @Override
    @Async
    public void create(CreateRequest req, Long roomId, String startDate, String endDate) {
        try {
            Reservation reservation = reservationRepository.findByRoomIdCheckInCheckOut(roomId, startDate, endDate);
            System.out.println(reservation);
            if(reservation == null || !reservation.getRoomId().equals(roomId)) {
                throw new ReservationException(ReservationErrorCode.RESERVE_NOT_FOUND);
            }
            List<ReservationDate> reservationDates = reservationDateRepository.findByReservationId(reservation.getId());
            reservation.setGuestCount(req.guestCount());
            reservation.setMessage(req.message());
            reservation.setTotal_money(req.totalMoney());
            reservation.setStatus(req.status());
            reservation.setReservationDates(reservationDates);
            reservationRepository.save(reservation);

            reservationCancel(reservation);
        } catch (ReservationException e) {
            System.out.println(e.getMessage());
        }
    }

    private  void reservationCancel(Reservation reservation) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            reservation.setStatus("cancel");
            reservationRepository.save(reservation);
            executorService.shutdown();
        }, 5, TimeUnit.MINUTES);
    }

    @Override
    public List<ReservationDate> createReservationDate(CreateDateRequest req, Long roomId) {
        String[] startDate = req.checkIn().split("-");
        String[] endDate = req.checkOut().split("-");
        Reservation reservation = reservationRepository.save(new Reservation(null, null, req.checkIn(), req.checkOut(), null, null,"pending", null, roomId));
        List<ReservationDate> reservationDates = reservationDateRepository.findByDateRange(reservation.getId(), startDate[2], endDate[2]);
        if(!reservationDates.isEmpty() && startDate[1].equals(reservationDates.get(0).getMonth()) && endDate[1].equals(reservationDates.get(reservationDates.size()-1).getMonth()))
            throw new IllegalArgumentException();
        List<ReservationDate> reservationDateList= calDate(req.checkIn(), req.checkOut(), reservation);
        return reservationDateRepository.saveAll(reservationDateList);
    }

    private List<ReservationDate> calDate(String checkIn, String checkOut, Reservation reservation) {
        LocalDate startDate = LocalDate.parse(checkIn);
        LocalDate endDate = LocalDate.parse(checkOut);
        long dayDiff = ChronoUnit.DAYS.between(startDate, endDate);
        List<ReservationDate> reservationDates = new ArrayList<>();
        for (int i = 0; i <= dayDiff; i++) {
            String[] date = startDate.plusDays(i).toString().split("-");
            ReservationDate reservationDate = ReservationDate.builder()
                    .year(date[0])
                    .month(date[1])
                    .day(date[2])
                    .reservation(reservation)
                    .build();
            reservationDates.add(reservationDate);
        }
        return reservationDates;
    }

    @Override
    public List<ReadResponse> getByReservationToUser(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        reservations.stream().map(reservation -> {

        })
        return List.of();
    }

    @Override
    public ReadResponse getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVE_NOT_FOUND));
        return new ReadResponse(reservation.getId(),
                reservation.getGuestCount(),
                reservation.getStartDate(), reservation.getEndDate(),
                reservation.getMessage(), reservation.getTotal_money(), reservation.getStatus());
    }

    @Override
    public void update(Long id, UpdateRequest req) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVE_NOT_FOUND));
        reservation.setGuestCount(req.guest_count());
        reservation.setStartDate(req.startDate());
        reservation.setEndDate(req.endDate());
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
