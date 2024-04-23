package com.air.room.controller;

import com.air.room.config.JwtTokenUtils;
import com.air.room.config.TokenInfo;
import com.air.room.dto.request.RoomRequest;
import com.air.room.global.domain.entity.Room;
import com.air.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRoom();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public void createRoom(
            @RequestBody RoomRequest req,
            @RequestHeader("Authorization") String bearerToken) {
        System.out.println("제발");
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        roomService.addRoom(tokenInfo.id(), tokenInfo.name(), req);
    }

    @GetMapping("/test/token")
    public String testToken() {
        return jwtTokenUtils.createToken(1,"qwer");
    }

}
