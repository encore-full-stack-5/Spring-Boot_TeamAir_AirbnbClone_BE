package com.air.room.dto.request;

import java.sql.Time;
import java.time.LocalDate;

public record RoomRequest(
        //userid는 토큰으로
        //city 넣어야함
        String name,
        String desc,
        Integer type,
        Integer maxPeople,
        Integer reserveOption,
        Integer bedroomNum,
        Integer bedNum,
        Integer bathroomNum,
        Integer price,
        Integer cleaningPrice,
        Time checkInTime,
        Time checkOutTime,
        String usingRule,
        LocalDate reserveStartAt,
        LocalDate reserveEndAt,

        RoomLocationRequest roomLocationRequest,
        SafetySupplyRequest safetySupplyRequest
) {
}
