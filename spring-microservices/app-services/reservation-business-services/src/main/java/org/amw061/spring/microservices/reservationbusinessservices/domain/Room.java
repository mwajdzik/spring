package org.amw061.spring.microservices.reservationbusinessservices.domain;

import lombok.Data;

@Data
public class Room {

    private Long id;
    private String name;
    private String roomNumber;
    private String bedInfo;
}
