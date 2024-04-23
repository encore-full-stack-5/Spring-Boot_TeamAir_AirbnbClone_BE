package com.air.room.dto.request;

import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.SafetySupply;

public record SafetySupplyRequest(
        Boolean fireAlram,
        Boolean aidKit,
        Boolean extinguisher,
        Boolean coAlram
) {
    public SafetySupply toEntity(Room room) {
        return SafetySupply.builder()
                .room(room)
                .fireAlram(fireAlram)
                .aidKit(aidKit)
                .extinguisher(extinguisher)
                .coAlram(coAlram)
                .build();
    }
}
