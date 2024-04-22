package com.air.room.service;

import com.air.room.dto.response.RoomRequest;
import com.air.room.global.domain.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRoom(Integer id);
    Room getRoomById(Integer id);
    void addRoom(RoomRequest req);
    void updateRoom(RoomRequest req);
}
