package com.air.room.component;

import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomSearchFilterTest {
    @Autowired
    private RoomSearchFilter roomSearchFilter;
    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testRoomSearchFilter() {
        List<Room> all = roomRepository.findAll();
        List<Room> list = roomSearchFilter.filter(all.stream())..toList();
        System.out.println(list.get(0).getName());

    }
}