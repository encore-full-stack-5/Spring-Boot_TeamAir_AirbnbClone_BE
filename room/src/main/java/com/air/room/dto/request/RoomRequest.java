package com.air.room.dto.request;

import com.air.room.global.domain.entity.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
    }

    public Room toEntity(Integer roomId, Integer userId, String userName) {
        return new Room(
                roomId,
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
    }

    public List<RoomAccessibility> getRoomAccessibilityList(Room room){
        List<RoomAccessibility> roomAccessibilityList = new ArrayList<>();
        for(Integer id : this.accessibility()){
            RoomAccessibility roomAccessibility = RoomAccessibility.builder()
                    .room(room)
                    .accessibility(Accessibility.builder()
                            .id(id)
                            .build())
                    .build();
            roomAccessibilityList.add(roomAccessibility);
        }
        return roomAccessibilityList;
    }

    public List<RoomAmenity> getRoomAmenityList(Room room){
        List<RoomAmenity> roomAmenityList = new ArrayList<>();
        for(Integer id : this.amenities()){
            RoomAmenity roomAmenity = RoomAmenity.builder()
                    .room(room)
                    .amenity(Amenity.builder()
                            .id(id)
                            .build())
                    .build();
            roomAmenityList.add(roomAmenity);
        }
        return roomAmenityList;
    }

    public List<RoomUniqueAmenity> getRoomUniqueAmenityList(Room room){
        List<RoomUniqueAmenity> roomUniqueAmenityList = new ArrayList<>();
        for(Integer id : this.uniqueAmenities()){
            RoomUniqueAmenity roomUniqueAmenity = RoomUniqueAmenity.builder()
                    .room(room)
                    .uniqueAmenity(UniqueAmenity.builder()
                            .id(id)
                            .build())
                    .build();
            roomUniqueAmenityList.add(roomUniqueAmenity);
        }
        return roomUniqueAmenityList;
    }

    public List<Integer> getDeleteRoomAmenityByIdList(Room room) {
        List<Integer> deleteRoomAmenityIdList = new ArrayList<>();
        List<Integer> amenityIdList = new ArrayList<>(Arrays.asList(this.amenities));
        room.getRoomAmenities().stream().map(e -> {
            Integer id = e.getAmenity().getId();
            if(!amenityIdList.contains(id)){
                deleteRoomAmenityIdList.add(id);
            }
            return null;
        });
        return deleteRoomAmenityIdList;
    }
    public List<RoomAmenity> getAddRoomAmenityList(Room room) {
        List<RoomAmenity> addRoomAmenityIdList = new ArrayList<>();
        List<Integer> amenityIdList =
                room.getRoomAmenities().stream().map(e -> e.getAmenity().getId()).toList();
        Arrays.stream(this.amenities).map(e -> {
            if(!amenityIdList.contains(e)) {
                addRoomAmenityIdList.add(RoomAmenity.builder()
                        .room(room)
                        .amenity(Amenity.builder()
                                .id(e)
                                .build())
                        .build());
            }
            return null;
        });
        return addRoomAmenityIdList;
    }

    public List<Integer> getDeleteRoomUniqueAmenityByIdList(Room room) {
        List<Integer> deleteRoomUniqueAmenityIdList = new ArrayList<>();
        List<Integer> UniqueAmenityIdList = new ArrayList<>(Arrays.asList(this.uniqueAmenities));
        room.getRoomUniqueAmenities().stream().map(e -> {
            Integer id = e.getUniqueAmenity().getId();
            if(!UniqueAmenityIdList.contains(id)){
                deleteRoomUniqueAmenityIdList.add(id);
            }
            return null;
        });
        return deleteRoomUniqueAmenityIdList;
    }
    public List<RoomUniqueAmenity> getAddRoomUniqueAmenityList(Room room) {
        List<RoomUniqueAmenity> addRoomUniqueAmenityIdList = new ArrayList<>();
        List<Integer> UniqueAmenityIdList =
                room.getRoomUniqueAmenities().stream().map(e -> e.getUniqueAmenity().getId()).toList();
        Arrays.stream(this.uniqueAmenities).map(e -> {
            if(!UniqueAmenityIdList.contains(e)) {
                addRoomUniqueAmenityIdList.add(RoomUniqueAmenity.builder()
                        .room(room)
                        .uniqueAmenity(UniqueAmenity.builder()
                                .id(e)
                                .build())
                        .build());
            }
            return null;
        });
        return addRoomUniqueAmenityIdList;
    }

    public List<Integer> getDeleteRoomAccessibilityByIdList(Room room) {
        List<Integer> deleteRoomAccessibilityIdList = new ArrayList<>();
        List<Integer> accessibilityIdList = new ArrayList<>(Arrays.asList(this.accessibility));
        room.getRoomAccessibility().stream().map(e -> {
            Integer id = e.getAccessibility().getId();
            if(!accessibilityIdList.contains(id)){
                deleteRoomAccessibilityIdList.add(id);
            }
            return null;
        });
        return deleteRoomAccessibilityIdList;
    }
    public List<RoomAccessibility> getAddRoomAccessibilityList(Room room) {
        List<RoomAccessibility> addRoomAccessibilityIdList = new ArrayList<>();
        List<Integer> accessibilityIdList =
                room.getRoomAccessibility().stream().map(e -> e.getAccessibility().getId()).toList();
        Arrays.stream(this.accessibility).map(e -> {
            if(!accessibilityIdList.contains(e)) {
                addRoomAccessibilityIdList.add(RoomAccessibility.builder()
                        .room(room)
                        .accessibility(Accessibility.builder()
                                .id(e)
                                .build())
                        .build());
            }
            return null;
        });
        return addRoomAccessibilityIdList;
    }
}
