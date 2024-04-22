package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_LOCATIONS")
@Builder
public class RoomLocation {
    @Id
    @Column(name = "location_id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "location_x", nullable = false)
    private BigInteger locationX;
    @Column(name = "location_y", nullable = false)
    private BigInteger locationY;
}
