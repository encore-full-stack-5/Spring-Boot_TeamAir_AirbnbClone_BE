package com.air.room.service;

import com.air.room.dto.request.RoomRequest;
import com.air.room.global.domain.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRoom();
    Room getRoomById(Integer id);
    void addRoom(Integer id, RoomRequest req);
    void updateRoom(RoomRequest req);
}
