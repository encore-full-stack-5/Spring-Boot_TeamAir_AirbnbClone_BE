package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCESSIBILITES")
@Builder
public class Accessibility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accessibilites_accessibility_id_seq")
    @SequenceGenerator(name = "accessibilites_accessibility_id_seq", sequenceName = "accessibilites_accessibility_id_seq", allocationSize = 1)
    @Column(name = "accessibility_id")
    private Integer id;
    @Column(name = "accessibility_name")
    private String name;

    @OneToMany(mappedBy = "accessibility")
    private List<RoomAccessibility> roomAccessibilities;
}
