package org.amw061.spring.microservices.reservationbusinessservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.collect.ImmutableList.of;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationBusinessServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationBusinessServicesApplication.class, args);
    }

    // for software load balancer Ribbon - even call distribution (it's better to use @FeignClient instead)
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .groupName("RoomReservation")
                .select()
                .apis(basePackage(ReservationBusinessServicesApplication.class.getPackage().getName()))
                .paths(any())
                .build()
                .apiInfo(new ApiInfo("Room Reservation Services", "A set of services to provide business processes for the Room and Reservations domains", "1.0.0",
                        null, new Contact("MW", "https://amw061.org", null), null, null, of()));
    }
}
