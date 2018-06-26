package org.amw061.spring.microservices.roomservices;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    @Column(name = "BED_INFO")
    private String bedInfo;
}
