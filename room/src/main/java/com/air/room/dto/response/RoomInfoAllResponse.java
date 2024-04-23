package com.air.room.dto.response;

import com.air.room.global.domain.dto.*;
import com.air.room.global.domain.entity.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record RoomInfoAllResponse (
        Integer id,
        Integer userId,
        String userName,
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
        List<RoomAccessibilityDto> accessibility,
        List<RoomAmenityDto> amenity,
        List<RoomUniqueAmenityDto> uniqueAmenity,
        RoomLocationDto location,
        SafetySupplyDto safetySupply
){
    public static RoomInfoAllResponse from(Room room) {
        List<RoomAccessibilityDto> accessibility = room.getRoomAccessibility().isEmpty() ? null :
                room.getRoomAccessibility().stream().map(
                roomAccessibility -> new RoomAccessibilityDto(
                        roomAccessibility.getId(),
                        roomAccessibility.getAccessibility().getName())
        ).toList();
        List<RoomAmenityDto> amenity = room.getRoomAmenities().isEmpty() ? null :
                room.getRoomAmenities().stream().map(
                roomAccessibility -> new RoomAmenityDto(
                        roomAccessibility.getId(),
                        roomAccessibility.getAmenity().getName())
        ).toList();
        List<RoomUniqueAmenityDto> uniqueAmenity = room.getRoomUniqueAmenities().isEmpty() ? null :
                room.getRoomUniqueAmenities().stream().map(
                roomAccessibility -> new RoomUniqueAmenityDto(
                        roomAccessibility.getId(),
                        roomAccessibility.getUniqueAmenity().getName())
        ).toList();
        RoomLocationDto location = room.getRoomLocation().isEmpty() ? null : new RoomLocationDto(
                room.getRoomLocation().get(0).getLocationX(),
                room.getRoomLocation().get(0).getLocationY()
        );
        SafetySupplyDto safetySupply = room.getSafetySupply().isEmpty() ? null : new SafetySupplyDto(
                room.getSafetySupply().get(0).getFireAlarm(),
                room.getSafetySupply().get(0).getAidKit(),
                room.getSafetySupply().get(0).getExtinguisher(),
                room.getSafetySupply().get(0).getCoAlarm()
        );
        return new RoomInfoAllResponse(
                room.getId(),
                room.getUserId(),
                room.getUserName(),
                room.getCity(),
                room.getName(),
                room.getDesc(),
                room.getType(),
                room.getMaxPeople(),
                room.getReserveOption(),
                room.getBedroomNum(),
                room.getBedNum(),
                room.getBathroomNum(),
                room.getPrice(),
                room.getCleaningPrice(),
                room.getCheckInTime(),
                room.getCheckOutTime(),
                room.getUsingRule(),
                room.getReserveStartAt(),
                room.getReserveEndAt(),
                accessibility,
                amenity,
                uniqueAmenity,
                location,
                safetySupply
        );
    }
}
