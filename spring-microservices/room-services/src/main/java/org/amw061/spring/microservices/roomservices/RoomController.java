package org.amw061.spring.microservices.roomservices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@RestController
@RequestMapping(value = "/rooms")
@Api(value = "rooms", description = "Data service operations on rooms", tags = ("rooms"))
public class RoomController {

    @Resource
    private RoomRepository roomRepository;

    @GetMapping
    @ApiOperation(value = "Get All Rooms", notes = "Gets all rooms in the system", nickname = "getRooms")
    public List<Room> findAll(@RequestParam(name = "roomNumber", required = false) String roomNumber) {
        try {
            return of(this.roomRepository.findByRoomNumber(roomNumber));
        } catch (Exception ex) {
            return this.roomRepository.findAll();
        }
    }
}
