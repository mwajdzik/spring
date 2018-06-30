package org.amw061.spring.microservices.webapp.client;

import org.amw061.spring.microservices.webapp.domain.Room;
import org.amw061.spring.microservices.webapp.domain.RoomReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("RESERVATION-BUSINESS-SERVICES")
public interface RoomReservationService {

    @GetMapping(value = "/rooms")
    List<Room> getAllRooms(@RequestParam(name = "roomNumber", required = false) String roomNumber);

    @GetMapping(value = "/roomReservations/{date}")
    List<RoomReservation> getRoomReservationsForDate(@PathVariable("date") String date);
}
