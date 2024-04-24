package com.air.room.utills;


import com.air.room.global.domain.entity.Room;

import java.util.function.Predicate;

public class FilterUtils {

    public static Predicate<Room> isCityCodeEqualTo(Integer cityCode) {
        return p -> cityCode == null || p.getCity().getCode().equals(cityCode);
    }
    public static Predicate<Room> isRoomTypeEqualTo(Integer roomType) {
        return p -> roomType == null || p.getType().equals(roomType);
    }
    public static Predicate<Room> isPersonNumMoreOrEqualTo(Integer personNum) {
        return p -> personNum == null || p.getMaxPeople() >= personNum;
    }
    public static Predicate<Room> isReserveOptionEqualTo(Integer reserveOption) {
        return p -> reserveOption == null || p.getReserveOption().equals(reserveOption);
    }
    public static Predicate<Room> isBedroomNumMoreOrEqualTo(Integer bedroomNum) {
        return p -> bedroomNum == null || p.getBedroomNum() >= bedroomNum;
    }
    public static Predicate<Room> isBedNumMoreOrEqualTo(Integer bedNum) {
        return p -> bedNum == null || p.getBedNum() >= bedNum;
    }
    public static Predicate<Room> isBathroomNumMoreOrEqualTo(Integer bathroomNum) {
        return p -> bathroomNum == null || p.getBathroomNum() >= bathroomNum;
    }
    public static Predicate<Room> isPriceMoreOrEqualTo(Integer price) {
        return p -> price == null || p.getPrice() >= price;
    }
    public static Predicate<Room> isPriceLessOrEqualTo(Integer price) {
        return p -> price == null || p.getPrice() <= price;
    }
}
