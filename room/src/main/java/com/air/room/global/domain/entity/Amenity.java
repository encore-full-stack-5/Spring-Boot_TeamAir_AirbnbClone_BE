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
@Table(name = "AMENITIES")
@Builder
public class Amenity{
    @Id
    @Column(name = "Amenity_id")
    private Integer id;
    @Column(name = "Amenity_name")
    private String name;
}
