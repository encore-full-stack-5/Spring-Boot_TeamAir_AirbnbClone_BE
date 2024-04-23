package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SAFETY_SUPPLIES")
@Builder
public class SafetySupply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "safety_supplies_safety_id_seq")
    @SequenceGenerator(name = "safety_supplies_safety_id_seq", sequenceName = "safety_supplies_safety_id_seq", allocationSize = 1)
    @Column(name="safety_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name="safety_fire_alram")
    private Boolean fireAlram;

    @Column(name="safety_aid_kit")
    private Boolean aidKit;

    @Column(name="safety_extinguisher")
    private Boolean extinguisher;

    @Column(name="safety_co_alram")
    private Boolean coAlram;
}
