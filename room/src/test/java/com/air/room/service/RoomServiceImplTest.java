package com.air.room.service;

import com.air.room.TestInit;
import com.air.room.dto.request.RoomLocationRequest;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.request.SafetySupplyRequest;
import com.air.room.global.domain.entity.City;
import com.air.room.global.domain.entity.Room;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceImplTest extends TestInit {
    @Autowired
    private RoomService roomService;
    @Autowired
    private EntityManager em;

    @Test
    void addRoom() {

        // give
        City city = City.builder()
                .id(1)
                .build();

        RoomRequest roomRequest = new RoomRequest(
                city,
                "숙소 설명",
                "숙소 이름",
                1,
                4,
                1,
                1,
                2,
                1,
                70000,
                10000,
                new Time(1000*60*60*7),
                new Time(1000*60*60*3),
                "사용 규칙",
                LocalDate.of(2024,5,1),
                LocalDate.of(2024,6,30),
                new RoomLocationRequest(
                        new BigDecimal("35.123456789"),
                        new BigDecimal("127.123456789")
                ),
                new SafetySupplyRequest(
                        true,
                        true,
                        false,
                        false
                )
        );

        // when
        roomService.addRoom(1, roomRequest);

        em.flush();
        em.clear();

        // then
        List<Room> allRoom = roomService.getAllRoom();
        Assertions.assertEquals(1, allRoom.size());
        System.out.println(allRoom.get(0).getName());
        System.out.println(allRoom.get(0).getSafetySupply().get(0).getRoom().getId());
        System.out.println(allRoom.get(0).getRoomLocation().get(0).getRoom().getId());
    }
}