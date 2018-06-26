package org.amw061.spring.microservices.roomservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumber(String roomNumber);
}
