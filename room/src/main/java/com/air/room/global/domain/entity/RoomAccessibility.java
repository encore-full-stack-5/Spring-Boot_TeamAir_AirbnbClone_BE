package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOM_ACCESSIBILITES")
@Builder
public class RoomAccessibility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_accessibilites_room_accessibility_id_seq")
    @SequenceGenerator(name = "room_accessibilites_room_accessibility_id_seq", sequenceName = "room_accessibilites_room_accessibility_id_seq", allocationSize = 1)
    @Column(name = "room_accessibility_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessibility_id", nullable = false)
    private Accessibility accessibility;
}
