package com.air.room.dto.request;

public record SafetySupplyRequest(
        Boolean fireAlram,
        Boolean aidKit,
        Boolean extinguisher,
        Boolean coAlram
) {
}
