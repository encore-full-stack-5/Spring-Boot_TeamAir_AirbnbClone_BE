package com.air.room.dto.request;

import com.air.room.global.domain.entity.City;
import com.air.room.global.domain.entity.Room;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record RoomRequest(
        //userid는 토큰으로
        Integer cityId,
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
        Integer checkInTime,
        Integer checkOutTime,
        String usingRule,
        String reserveStartAt,
        String reserveEndAt,

        Integer[] accessibility,
        Integer[] amenities,
        Integer[] uniqueAmenities,
        RoomLocationRequest roomLocationRequest,
        SafetySupplyRequest safetySupplyRequest
) {
    public Room toEntity(Integer userId, String userName) {
        return new Room(
                null,
                userId,
                userName,
                City.builder().id(cityId).build(),
                name,
                desc,
                type,
                maxPeople,
                reserveOption,
                bedroomNum,
                bedNum,
                bathroomNum,
                price,
                cleaningPrice,
                new Time(checkInTime),
                new Time(checkOutTime),
                usingRule,
                LocalDate.parse(reserveStartAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(reserveEndAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                null,
                null,
                null,
                null,
                null
        );
//        return Room.builder()
//                .userId(userId)
//                .userName(userName)
//                .city(City.builder()
//                        .id(cityId)
//                        .build())
//                .name(name)
//                .desc(desc)
//                .type(type)
//                .maxPeople(maxPeople)
//                .reserveOption(reserveOption)
//                .bedroomNum(bedroomNum)
//                .bedNum(bedNum)
//                .bathroomNum(bathroomNum)
//                .price(price)
//                .cleaningPrice(cleaningPrice)
//                .checkInTime(new Time(checkInTime))
//                .checkOutTime(new Time(checkOutTime))
//                .usingRule(usingRule)
//                .reserveStartAt(LocalDate.parse(reserveStartAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .reserveEndAt(LocalDate.parse(reserveEndAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .build();
    }
}
