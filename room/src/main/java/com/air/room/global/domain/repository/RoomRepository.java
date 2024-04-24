package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository
        extends JpaRepository<Room, Integer> {
}
