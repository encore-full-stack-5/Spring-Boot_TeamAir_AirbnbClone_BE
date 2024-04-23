package com.air.room.dto.request;

import com.air.room.global.domain.entity.City;
import com.air.room.global.domain.entity.Room;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record RoomRequest(
        //userid는 토큰으로
        City city,

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

        Integer[] accessibility,
        Integer[] amenities,
        Integer[] uniqueAmenities,
        RoomLocationRequest roomLocationRequest,
        SafetySupplyRequest safetySupplyRequest
) {
    public Room toEntity(Integer userId, String userName) {
        return Room.builder()
                .userId(userId)
                .userName(userName)
                .city(city)
                .name(name)
                .desc(desc)
                .type(type)
                .maxPeople(maxPeople)
                .reserveOption(reserveOption)
                .bedroomNum(bedroomNum)
                .bedNum(bedNum)
                .bathroomNum(bathroomNum)
                .price(price)
                .cleaningPrice(cleaningPrice)
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .usingRule(usingRule)
                .reserveStartAt(reserveStartAt)
                .reserveEndAt(reserveEndAt)
                .build();
    }
}
