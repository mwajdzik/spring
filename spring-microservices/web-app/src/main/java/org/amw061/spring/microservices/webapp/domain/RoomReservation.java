package org.amw061.spring.microservices.webapp.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomReservation {

    private Long roomId;
    private Long guestId;
    private String roomName;
    private String roomNumber;
    private String firstName;
    private String lastName;
    private String date;
}
