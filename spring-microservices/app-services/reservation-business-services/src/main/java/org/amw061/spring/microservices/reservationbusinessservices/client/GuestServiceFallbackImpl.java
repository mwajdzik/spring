package org.amw061.spring.microservices.reservationbusinessservices.client;

import com.google.common.collect.ImmutableList;
import org.amw061.spring.microservices.reservationbusinessservices.domain.Guest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuestServiceFallbackImpl implements GuestService {
    @Override
    public List<Guest> findAll(String emailAddress) {
        return ImmutableList.of();
    }

    @Override
    public Guest findOne(long id) {
        Guest guest = new Guest();
        guest.setFirstName("Guest");
        guest.setLastName("Occupied");
        return guest;
    }
}
