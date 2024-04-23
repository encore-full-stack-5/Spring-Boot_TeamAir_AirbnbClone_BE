package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_UNIQUE_AMENITIES")
@Builder
public class RoomUniqueAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_unique_amenities_room_unique_amenity_id_seq")
    @SequenceGenerator(name = "room_unique_amenities_room_unique_amenity_id_seq", sequenceName = "room_unique_amenities_room_unique_amenity_id_seq", allocationSize = 1)
    @Column(name = "ROOM_UNIQUE_AMENITY_ID", columnDefinition = "serial")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIQUE_AMENITy_id", nullable = false)
    private UniqueAmenity uniqueAmenity;
}
