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
    @Column(name = "ROOM_AMENITY_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "AMENITY_id")
    private Amenity amenity;
}
