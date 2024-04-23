package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.RoomLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomLocationRepository
        extends JpaRepository<RoomLocation, Integer> {

    RoomLocation findByRoomId(Integer roomId);
}
