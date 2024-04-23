package com.air.room.service;

import com.air.room.dto.request.RoomRequest;
import com.air.room.exception.NotFoundException;
import com.air.room.global.domain.entity.*;
import com.air.room.global.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomLocationRepository roomLocationRepository;
    private final SafetySupplyRepository safetySupplyRepository;
    private final RoomAccessibilityRepository roomAccessibilityRepository;
    private final RoomAmenityRepository roomAmenityRepository;
    private final RoomUniqueAmenityRepository roomUniqueAmenityRepository;

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException("ROOM"));
    }

    @Override
    public void addRoom(Integer userId, String userName, RoomRequest req) {
        Room room = req.toEntity(userId, userName);
        roomRepository.save(room);

        RoomLocation roomLocation = req.roomLocationRequest().toEntity(room);
        roomLocationRepository.save(roomLocation);

        SafetySupply safetySupply = req.safetySupplyRequest().toEntity(room);
        safetySupplyRepository.save(safetySupply);

        for(Integer id : req.accessibility()){
            RoomAccessibility roomAccessibility = RoomAccessibility.builder()
                    .room(room)
                    .accessibility(Accessibility.builder()
                            .id(id)
                            .build())
                    .build();
            roomAccessibilityRepository.save(roomAccessibility);
        }
        for(Integer id : req.amenities()){
            RoomAmenity roomAmenity = RoomAmenity.builder()
                    .room(room)
                    .amenity(Amenity.builder()
                            .id(id)
                            .build())
                    .build();
            roomAmenityRepository.save(roomAmenity);
        }
        for(Integer id : req.uniqueAmenities()){
            RoomUniqueAmenity roomUniqueAmenity = RoomUniqueAmenity.builder()
                    .room(room)
                    .uniqueAmenity(UniqueAmenity.builder()
                            .id(id)
                            .build())
                    .build();
            roomUniqueAmenityRepository.save(roomUniqueAmenity);
        }
    }

    @Override
    public void updateRoom(RoomRequest req) {

    }
}
