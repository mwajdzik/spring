package org.amw061.spring.microservices.reservationbusinessservices.client;

import org.amw061.spring.microservices.reservationbusinessservices.domain.Guest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("GUEST-SERVICES")
public interface GuestService {

    @GetMapping(value = "/guests")
    List<Guest> findAll(@RequestParam(name = "emailAddress", required = false) String emailAddress);

    @GetMapping(value = "/guests/{id}")
    Guest findOne(@PathVariable(name = "id") long id);
}
