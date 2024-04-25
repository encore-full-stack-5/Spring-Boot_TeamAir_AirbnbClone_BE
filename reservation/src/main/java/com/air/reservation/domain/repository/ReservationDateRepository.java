package com.air.reservation.domain.repository;

import com.air.reservation.domain.entity.ReservationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationDateRepository extends JpaRepository<ReservationDate, Long> {
    @Query("SELECT rd FROM ReservationDate rd WHERE rd.day >= :checkIn and rd.day <= :checkOut")
    List<ReservationDate> findByDateRange(@Param("checkIn") String checkIn, @Param("checkOut") String checkOut);
}
