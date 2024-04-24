package com.air.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @NoArgsConstructor
@AllArgsConstructor
@Table @Builder
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    @Column(name = "guest_count")
    @Setter
    private Integer guestCount;
    @Column(name = "check_in")
    @Setter
    private LocalDate startDate;
    @Column(name = "check_out")
    @Setter
    private LocalDate endDate;
    @Column(name = "reserve_status")
    @ColumnDefault("false")
    private boolean reserveStatus;
    @Column(name = "message")
    @Setter
    private String message;
    @Column()
    @Setter
    private Integer total_money;
    @OneToMany
    private List<ReservationDate> reservationDates;
}
