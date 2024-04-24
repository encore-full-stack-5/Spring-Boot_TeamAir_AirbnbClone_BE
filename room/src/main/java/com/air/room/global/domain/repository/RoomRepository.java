package com.air.room.global.domain.repository;

import com.air.room.global.domain.entity.Room;
import com.air.room.global.domain.entity.RoomAccessibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository
        extends JpaRepository<Room, Integer> {

    public List<Room> findAllByUserId(Integer userId);
}
