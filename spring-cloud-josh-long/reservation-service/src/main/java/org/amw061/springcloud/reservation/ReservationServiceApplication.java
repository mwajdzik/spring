package org.amw061.springcloud.reservation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }
}

// http://localhost:8000/message
@RestController
@RefreshScope
class MessageRestController {

    @Value("${message}") private String message;
    @Value("${server.port}") private String port;

    @GetMapping("/message")
    public String read() {
        return message + " (" + port + ")";
    }
}

@Component
class SampleDataClr implements CommandLineRunner {

    private final ReservationRepository reservationRepository;

    @Autowired
    SampleDataClr(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Maciej", "Madzia", "Kuba").forEach(name ->
                reservationRepository.save(new Reservation(name)));

        reservationRepository.findAll().forEach(System.out::println);
    }
}

// http://localhost:8000/reservations
@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

@Data
@Entity
class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private String reservationName;

    Reservation() {
    }

    Reservation(String name) {
        this.reservationName = name;
    }
}