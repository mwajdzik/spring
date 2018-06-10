package org.amw061.springcloud.reservationclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.hateoas.Resources;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@IntegrationComponentScan
@EnableBinding(ReservationChannels.class)
public class ReservationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationClientApplication.class, args);
    }
}

@FeignClient("reservation-service")
interface ReservationReader {

    @GetMapping("/reservations")
    Resources<Reservation> read();
}

interface ReservationChannels {

    @Output
    MessageChannel output(); // we could have more, eg. orders, customers, products
}

@MessagingGateway
interface ReservationWriter {

    // output channel defined in ReservationChannels.output
    @Gateway(requestChannel = "output")
    void write(String reservation);
}

@Data
class Reservation {
    private String reservationName;
}

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@RequestMapping("/reservations")
class ReservationApiGateway {

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection"})
    @Autowired
    private ReservationReader reservationReader;

    @Autowired
    private ReservationWriter reservationWriter;

    @SuppressWarnings("unused")
    public List<String> fallback() {
        return emptyList();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/names")
    public List<String> names() {
        return reservationReader.read()
                .getContent()
                .stream()
                .map(Reservation::getReservationName)
                .collect(toList());
    }

    @PostMapping
    public void write(@RequestBody Reservation reservation) {
        reservationWriter.write(reservation.getReservationName());
    }
}