package com.air.room.service;

import com.air.room.config.TokenInfo;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.response.RoomInfoAllResponse;
import com.air.room.exception.NotFoundException;
import com.air.room.global.domain.entity.*;
import com.air.room.global.domain.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<RoomInfoAllResponse> getAllRoom() {
        return roomRepository.findAll().stream().map(RoomInfoAllResponse::from).toList();
    }

    @Override
    public RoomInfoAllResponse getRoomById(Integer id) {
//        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException("ROOM"));
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("ROOM"));
        return RoomInfoAllResponse.from(room);
    }

    @Override
    @Transactional
    public void addRoom(Integer userId, String userName, RoomRequest req) {
        Room room = req.toEntity(userId, userName);
        roomRepository.save(room);

        RoomLocation roomLocation = req.roomLocationRequest().toEntity(room);
        roomLocationRepository.save(roomLocation);

        SafetySupply safetySupply = req.safetySupplyRequest().toEntity(room);
        safetySupplyRepository.save(safetySupply);

        /*for(Integer id : req.accessibility()){
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
        }*/

        roomAccessibilityRepository.saveAll(req.getRoomAccessibilityList(room));
        roomAmenityRepository.saveAll(req.getRoomAmenityList(room));
        roomUniqueAmenityRepository.saveAll(req.getRoomUniqueAmenityList(room));
    }

    @Override
    @Transactional
    public void updateRoom(Integer roomId, TokenInfo tokenInfo, RoomRequest req) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("ROOM"));
        Room roomReq = req.toEntity(roomId, tokenInfo.id(), tokenInfo.name());
        room.update(roomReq);

        SafetySupply safetySupply = safetySupplyRepository.findByRoomId(roomId);
        safetySupply.update(roomReq.getSafetySupply().get(0));

        RoomLocation roomLocation = roomLocationRepository.findByRoomId(roomId);
        roomLocation.update(roomReq.getRoomLocation().get(0));

        roomAmenityRepository.deleteAllById(req.getDeleteRoomAmenityByIdList(room));
        roomAmenityRepository.saveAll(req.getAddRoomAmenityList(room));

        roomUniqueAmenityRepository.deleteAllById(req.getDeleteRoomUniqueAmenityByIdList(room));
        roomUniqueAmenityRepository.saveAll(req.getAddRoomUniqueAmenityList(room));

        roomAccessibilityRepository.deleteAllById(req.getDeleteRoomAccessibilityByIdList(room));
        roomAccessibilityRepository.saveAll(req.getAddRoomAccessibilityList(room));

        /*List<Integer> roomReqAccessibility = roomReq.getRoomAccessibility().stream().map(
                e -> e.getAccessibility().getId()).toList();
        room.getRoomAccessibility().forEach(e -> {
            if(!roomReqAccessibility.contains(e.getAccessibility().getId())) {
                roomAmenityRepository.deleteById(e.getId());
            }
        });
        List<Integer> roomAccessibility = room.getRoomAccessibility().stream().map(
                e -> e.getAccessibility().getId()).toList();
        roomReq.getRoomAccessibility().forEach(e -> {
            if(!roomAccessibility.contains(e.getAccessibility().getId())) {
                roomAmenityRepository.save(RoomAmenity.builder()
                        .room(room)
                        .amenity(Amenity.builder()
                                .id(e.getAccessibility().getId())
                                .build())
                        .build());
            }
        });*/
    }

    @Override
    @Transactional
    public void deleteRoom(Integer roomId) {
        roomRepository.deleteById(roomId);
    }
}
