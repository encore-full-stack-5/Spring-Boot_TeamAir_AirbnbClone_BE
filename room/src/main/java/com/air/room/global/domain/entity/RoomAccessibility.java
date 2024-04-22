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
    @Column(name = "ROOM_ACCESSIBILITy_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "accessibility_id")
    private Accessibility accessibility;
}
