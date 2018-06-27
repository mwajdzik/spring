package org.amw061.spring.microservices.reservationservices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
@Api(value = "reservations", description = "Data service operations on reservations", tags = ("reservations"))
public class ReservationController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private ReservationRepository reservationRepository;

    @GetMapping
    @ApiOperation(value = "Get All Reservations", notes = "Gets all reservations in the system", nickname = "getReservations")
    public List<Reservation> findAll(@RequestParam(name = "date", required = false) String date) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(DATE_FORMAT.parse(date).getTime());
            return this.reservationRepository.findByDate(sqlDate);
        } catch (Exception ex) {
            return this.reservationRepository.findAll();
        }
    }
}
