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
@Table(name = "ROOM_UNIQUE_AMENITIES")
@Builder
public class RoomUniqueAmenity {
    @Id
    @Column(name = "ROOM_UNIQUE_AMENITy_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "UNIQUE_AMENITy_id")
    private UniqueAmenity uniqueAmenity;
}
