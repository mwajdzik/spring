package org.amw061.spring.microservices.reservationbusinessservices.domain;

import lombok.Data;

@Data
public class Reservation {

    private Long id;
    private Long roomId;
    private Long guestId;
    private String date;
}
