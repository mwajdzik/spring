package org.amw061.spring.microservices.guestservices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ImmutableList.of;

@RestController
@RequestMapping(value = "/guests")
@Api(value = "guests", description = "Data service operations on guests", tags = ("guests"))
public class GuestController {

    @Resource
    private GuestRepository guestRepository;

    @GetMapping
    @ApiOperation(value = "Get All Guests", notes = "Gets all guests in the system", nickname = "getGuests")
    public List<Guest> findAll(@RequestParam(name = "emailAddress", required = false) String emailAddress) {
        try {
            return of(this.guestRepository.findByEmailAddress(emailAddress));
        } catch (Exception ex) {
            return this.guestRepository.findAll();
        }
    }

    @GetMapping(value = "/{id}")
    public Optional<Guest> findOne(@PathVariable(name = "id") long id) {
        return this.guestRepository.findById(id);
    }
}
