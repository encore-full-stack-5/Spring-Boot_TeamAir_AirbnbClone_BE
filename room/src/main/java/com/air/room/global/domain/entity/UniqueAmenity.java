package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UNIQUE_AMENITIES")
@Builder
public class UniqueAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unique_amenities_unique_amenity_id_seq")
    @SequenceGenerator(name = "unique_amenities_unique_amenity_id_seq", sequenceName = "unique_amenities_unique_amenity_id_seq", allocationSize = 1)
    @Column(name = "unique_amenity_id")
    private Integer id;
    @Column(name = "unique_amenity_name")
    private String name;

    @OneToMany(mappedBy = "uniqueAmenity")
    private List<RoomUniqueAmenity> roomUniqueAmenities;
}