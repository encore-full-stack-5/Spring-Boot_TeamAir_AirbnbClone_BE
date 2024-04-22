package com.air.room.global.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UNIQUE_AMENITIES")
@Builder
public class UniqueAmenity {
    @Id
    @Column(name = "unique_amenity_id")
    private Integer id;
    @Column(name = "unique_amenity_name")
    private String name;
}