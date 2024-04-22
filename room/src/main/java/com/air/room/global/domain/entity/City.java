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
@Table(name = "CITES")
@Builder
public class City {
    @Id
    @Column(name = "city_id")
    private Integer id;
    @Column(name = "city_code")
    private Integer code;
    @Column(name = "city_name")
    private String name;
}
