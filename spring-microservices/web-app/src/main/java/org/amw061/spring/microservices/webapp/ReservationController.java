package org.amw061.spring.microservices.webapp;

import org.amw061.spring.microservices.webapp.client.RoomReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang.StringUtils.isBlank;

// http://localhost:8600/?date=2017-01-01

@Controller
public class ReservationController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Resource private RoomReservationService roomReservationService;

    @GetMapping
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
        String date = isBlank(dateString) ? DATE_FORMAT.format(new Date()) : dateString;
        model.addAttribute("roomReservations", this.roomReservationService.getRoomReservationsForDate(date));
        return "reservations";
    }
}
