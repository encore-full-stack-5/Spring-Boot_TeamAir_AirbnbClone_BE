package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AMENITIES")
@Builder
public class Amenity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "amenities_amenity_id_seq")
    @SequenceGenerator(name = "amenities_amenity_id_seq", sequenceName = "amenities_amenity_id_seq", allocationSize = 1)
    @Column(name = "Amenity_id")
    private Integer id;
    @Column(name = "Amenity_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "amenity")
    private List<RoomAmenity> roomAmenities;
}
