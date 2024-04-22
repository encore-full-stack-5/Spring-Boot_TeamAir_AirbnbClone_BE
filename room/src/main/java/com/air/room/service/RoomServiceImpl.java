package com.air.room.service;

import com.air.room.dto.response.RoomRequest;
import com.air.room.global.domain.entity.Room;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    @Override
    public List<Room> getRoom(Integer id) {
        return List.of();
    }

    @Override
    public Room getRoomById(Integer id) {
        return null;
    }

    @Override
    public void addRoom(RoomRequest req) {

    }

    @Override
    public void updateRoom(RoomRequest req) {

    }
}
