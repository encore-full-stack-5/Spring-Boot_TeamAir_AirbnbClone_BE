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
@Table(name = "ACCESSIBILITES")
@Builder
public class Accessibility {
    @Id
    @Column(name = "accessibility_id")
    private Integer id;
    @Column(name = "accessibility_name")
    private String name;
}
