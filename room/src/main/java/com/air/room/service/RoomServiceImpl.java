package com.air.room.service;

import com.air.room.dto.request.RoomRequest;
import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;


    @Override
    public List<Room> getRoom(Integer id) {
        return List.of();
    }

    @Override
    public Room getRoomById(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow();
        return room;
    }

    @Override
    public void addRoom(RoomRequest req) {

    }

    @Override
    public void updateRoom(RoomRequest req) {

    }
}
