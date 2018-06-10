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
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
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

@Data
class Reservation {
    private String reservationName;
}

@RestController
@RequestMapping("/reservations")
class ReservationApiGateway {

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
    @Autowired
    private ReservationReader reservationReader;

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
}