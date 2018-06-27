package org.amw061.spring.microservices.reservationbusinessservices.client;

import org.amw061.spring.microservices.reservationbusinessservices.domain.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// we are still using Ribbon and making REST requests
@FeignClient(name = "ROOM-SERVICES")
public interface RoomService {

    // exactly the same interface as in RestController
    @GetMapping(value = "/rooms")
    List<Room> findAll(@RequestParam(name = "roomNumber", required = false) String roomNumber);
}
