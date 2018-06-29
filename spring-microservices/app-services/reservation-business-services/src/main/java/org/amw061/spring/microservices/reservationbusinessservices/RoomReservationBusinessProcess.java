package org.amw061.spring.microservices.reservationbusinessservices;

import org.amw061.spring.microservices.reservationbusinessservices.client.GuestService;
import org.amw061.spring.microservices.reservationbusinessservices.client.ReservationService;
import org.amw061.spring.microservices.reservationbusinessservices.client.RoomService;
import org.amw061.spring.microservices.reservationbusinessservices.domain.Room;
import org.amw061.spring.microservices.reservationbusinessservices.domain.RoomReservation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;

@Service
public class RoomReservationBusinessProcess {

    @Resource private GuestService guestService;
    @Resource private RoomService roomService;
    @Resource private ReservationService reservationService;

    Collection<RoomReservation> getRoomReservationsForDate(String dateString) {
        Map<Long, RoomReservation> roomReservationMap = this.roomService.findAll(null).stream()
                .collect(toMap(Room::getId, room -> RoomReservation.builder()
                        .roomId(room.getId())
                        .roomName(room.getName())
                        .roomNumber(room.getRoomNumber())
                        .build()));


        ofNullable(this.reservationService.findAll(dateString)).ifPresent(reservations ->
                reservations.forEach(reservation -> ofNullable(this.guestService.findOne(reservation.getGuestId()))
                        .ifPresent(guest -> {
                            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
                            roomReservation.setDate(reservation.getDate());
                            roomReservation.setFirstName(guest.getFirstName());
                            roomReservation.setLastName(guest.getLastName());
                            roomReservation.setGuestId(guest.getId());
                        })));

        return roomReservationMap.values();
    }
}
