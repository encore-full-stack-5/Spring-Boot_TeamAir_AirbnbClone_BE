package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CITES")
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cites_city_id_seq")
    @SequenceGenerator(name = "cites_city_id_seq", sequenceName = "cites_city_id_seq", allocationSize = 1)
    @Column(name = "city_id")
    private Integer id;
    @Column(name = "city_code")
    private Integer code;
    @Column(name = "city_name")
    private String name;
}
