package com.air.room.service;

import com.air.room.dto.request.RoomRequest;
import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.RoomLocation;
import com.air.room.global.domain.entity.SafetySupply;
import com.air.room.global.domain.repository.RoomLocationRepository;
import com.air.room.global.domain.repository.RoomRepository;
import com.air.room.global.domain.repository.SafetySupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomLocationRepository roomLocationRepository;
    private final SafetySupplyRepository safetySupplyRepository;

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).orElseThrow();
    }

    @Override
    public void addRoom(Integer userId, RoomRequest req) {
        Room room = req.toEntity(userId);
        roomRepository.save(room);
        RoomLocation roomLocation = req.roomLocationRequest().toEntity(room);
        roomLocationRepository.save(roomLocation);
        SafetySupply safetySupply = req.safetySupplyRequest().toEntity(room);
        safetySupplyRepository.save(safetySupply);
    }

    @Override
    public void updateRoom(RoomRequest req) {

    }
}
