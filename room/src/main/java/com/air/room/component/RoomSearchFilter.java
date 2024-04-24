package com.air.room.component;


import com.air.room.global.domain.entity.Room;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class RoomSearchFilter {
    static Function<Stream<Room>, Stream<Room>> filter =
            e -> e.filter(room -> room.getCity().getCode().equals(111111));

    public static Stream<Room> filter(Stream<Room> rooms) {
        return filter.apply(rooms);
    }

}
