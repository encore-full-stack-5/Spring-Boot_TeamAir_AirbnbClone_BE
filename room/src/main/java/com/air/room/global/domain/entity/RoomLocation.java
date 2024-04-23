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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_locations_location_id_seq")
    @SequenceGenerator(name = "room_locations_location_id_seq", sequenceName = "room_locations_location_id_seq", allocationSize = 1)
    @Column(name = "location_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "location_x", nullable = false)
    private Long locationX;
    @Column(name = "location_y", nullable = false)
    private Long locationY;
}
