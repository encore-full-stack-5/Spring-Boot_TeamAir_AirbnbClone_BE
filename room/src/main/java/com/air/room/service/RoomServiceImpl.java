package com.air.room.service;

import com.air.room.utills.FilterUtils;
import com.air.room.config.TokenInfo;
import com.air.room.dto.SearchRoomDto;
import com.air.room.dto.request.RoomRequest;
import com.air.room.dto.response.RoomInfoAllResponse;
import com.air.room.exception.DisabledArgumentException;
import com.air.room.exception.NotFoundException;
import com.air.room.global.domain.entity.*;
import com.air.room.global.domain.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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
        return roomRepository.findAll().stream()
                .filter(room -> !room.getIsDisable())
                .map(RoomInfoAllResponse::from)
                .toList();
    }

    @Override
    public RoomInfoAllResponse getRoomById(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("ROOM"));
        if (room.getIsDisable()) throw new DisabledArgumentException("ROOM");
        return RoomInfoAllResponse.from(room);
    }

    @Override
    public List<RoomInfoAllResponse> getAllRoomByUserId(Integer id) {
        return roomRepository.findAllByUserId(id).stream()
                .filter(room -> !room.getIsDisable())
                .map(RoomInfoAllResponse::from)
                .toList();
    }

    @Override
    public List<RoomInfoAllResponse> searchRoom(SearchRoomDto opt) {
        Stream<Room> allRoom = roomRepository.findAll().stream();

        return allRoom
                .filter(room -> !room.getIsDisable())
                .filter(FilterUtils.isCityCodeEqualTo(opt.cityCode()))
                .filter(FilterUtils.isRoomTypeEqualTo(opt.roomType()))
                .filter(FilterUtils.isPersonNumMoreOrEqualTo(opt.personNum()))
                .filter(FilterUtils.isReserveOptionEqualTo(opt.roomReserve()))
                .filter(FilterUtils.isBedroomNumMoreOrEqualTo(opt.bedroomNum()))
                .filter(FilterUtils.isBedNumMoreOrEqualTo(opt.bedNum()))
                .filter(FilterUtils.isBathroomNumMoreOrEqualTo(opt.bathroomNum()))
                .filter(FilterUtils.isPriceMoreOrEqualTo(opt.minPrice()))
                .filter(FilterUtils.isPriceLessOrEqualTo(opt.maxPrice()))
                .map(RoomInfoAllResponse::from)
                .toList();

        /* before
        if (opt.cityCode() != null)
            allRoom = allRoom.filter(room -> room.getCity().getCode().equals(opt.cityCode()));
        if (opt.roomType() != null)
            allRoom = allRoom.filter(room -> room.getType().equals(opt.roomType()));
        if (opt.personNum() != null)
            allRoom = allRoom.filter(room -> room.getMaxPeople() >= opt.personNum());
        if (opt.roomReserve() != null)
            allRoom = allRoom.filter(room -> room.getReserveOption().equals(opt.roomReserve()));
        if (opt.bedroomNum() != null)
            allRoom = allRoom.filter(room -> room.getBedroomNum() >= opt.bedroomNum());
        if (opt.bedNum() != null)
            allRoom = allRoom.filter(room -> room.getBedNum() >= opt.bedNum());
        if (opt.bathroomNum() != null)
            allRoom = allRoom.filter(room -> room.getBathroomNum() >= opt.bathroomNum());
        if (opt.minPrice() != null)
            allRoom = allRoom.filter(room -> room.getPrice() >= opt.minPrice());
        if (opt.maxPrice() != null)
            allRoom = allRoom.filter(room -> room.getPrice() <= opt.maxPrice());
        //reserveStart
        //reserveEnd

        return allRoom
                .filter(room -> !room.getIsDisable())
                .map(RoomInfoAllResponse::from)
                .toList();
        */
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
        if (room.getIsDisable()) throw new DisabledArgumentException("ROOM");
        Room roomReq = req.toEntity(roomId, tokenInfo.id(), tokenInfo.name());
        room.update(roomReq);

        RoomLocation roomLocation = roomLocationRepository.findByRoomId(roomId);
//        roomLocation.update(roomReq.getRoomLocation().get(0));
        roomLocation.update(req.roomLocationRequest().toEntity(roomReq));

        SafetySupply safetySupply = safetySupplyRepository.findByRoomId(roomId);
//        safetySupply.update(roomReq.getSafetySupply().get(0));
        safetySupply.update(req.safetySupplyRequest().toEntity(roomReq));

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
//        roomRepository.deleteById(roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("ROOM"));
        room.disable();
    }
}
