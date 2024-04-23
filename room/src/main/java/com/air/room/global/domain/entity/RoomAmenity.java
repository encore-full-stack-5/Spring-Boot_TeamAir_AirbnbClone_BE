package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_AMENITIES")
@Builder
public class RoomAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_amenities_room_amenity_id_seq")
    @SequenceGenerator(name = "room_amenities_room_amenity_id_seq", sequenceName = "room_amenities_room_amenity_id_seq", allocationSize = 1)
    @Column(name = "ROOM_AMENITY_id", columnDefinition = "serial")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AMENITY_id")
    private Amenity amenity;
}
