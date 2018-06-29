package org.amw061.spring.microservices.reservationbusinessservices.client;

import org.amw061.spring.microservices.reservationbusinessservices.domain.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// we are still using Ribbon and making REST requests using @FeignClient
@FeignClient(name = "ROOM-SERVICES")
public interface RoomService {

    // exactly the same interface as in RestController
    @GetMapping(value = "/rooms")
    List<Room> findAll(@RequestParam(name = "roomNumber", required = false) String roomNumber);

    @GetMapping(value = "/rooms/{id}")
    Room findOne(@PathVariable("id") long id);
}
