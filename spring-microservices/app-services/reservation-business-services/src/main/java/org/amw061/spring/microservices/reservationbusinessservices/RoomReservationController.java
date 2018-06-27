package org.amw061.spring.microservices.reservationbusinessservices;

import org.amw061.spring.microservices.reservationbusinessservices.client.RoomService;
import org.amw061.spring.microservices.reservationbusinessservices.domain.Room;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class RoomReservationController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RoomService roomService;

    @GetMapping(value = "/rooms")
    public List<Room> getAllRooms() {
        ResponseEntity<List<Room>> roomResponse = restTemplate.exchange("http://ROOM-SERVICES/rooms",
                GET, null, new ParameterizedTypeReference<List<Room>>() {});

        return roomResponse.getBody();
    }

    @GetMapping(value = "/rooms_feign")
    public List<Room> getAllRoomsFeign() {
        return roomService.findAll(null);
    }
}
