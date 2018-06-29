package org.amw061.spring.microservices.reservationbusinessservices;

import io.swagger.annotations.ApiOperation;
import org.amw061.spring.microservices.reservationbusinessservices.client.RoomService;
import org.amw061.spring.microservices.reservationbusinessservices.domain.Room;
import org.amw061.spring.microservices.reservationbusinessservices.domain.RoomReservation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class RoomReservationController {

    @Resource private RestTemplate restTemplate;
    @Resource private RoomService roomService;
    @Resource private RoomReservationBusinessProcess businessProcess;

    @GetMapping(value = "/rooms_with_rest_template")
    public List<Room> getAllRoomsRestTemplate() {
        ResponseEntity<List<Room>> roomResponse = restTemplate.exchange("http://ROOM-SERVICES/rooms",
                GET, null, new ParameterizedTypeReference<List<Room>>() {});

        return roomResponse.getBody();
    }

    @GetMapping(value = "/rooms_with_feign")
    public List<Room> getAllRoomsFeign() {
        return roomService.findAll(null);
    }

    @GetMapping(value = "/rooms")
    @ApiOperation(value = "Get All Rooms", notes = "Gets all rooms in the system", nickname = "getRooms")
    public List<Room> getAllRooms(@RequestParam(name = "roomNumber", required = false) String roomNumber) {
        return roomService.findAll(roomNumber);
    }

    @GetMapping(value = "/roomReservations/{date}")
    @ApiOperation(value = "Get Room Reservations", notes = "Gets all reservations for a specific date", nickname = "getReservationsForDate")
    public Collection<RoomReservation> getRoomReservationsForDate(@PathVariable("date") String date) {
        return this.businessProcess.getRoomReservationsForDate(date);
    }
}
