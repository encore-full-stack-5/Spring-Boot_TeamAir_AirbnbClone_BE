package com.air.room.api;

import com.air.room.dto.request.CreateDateRequest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("RESERVATION")
public interface FeignReservation {
    @PostMapping("/date/create/{rid}")
    Object createReservationDate(CreateDateRequest req, @PathVariable("rid") Long roomId);

}
