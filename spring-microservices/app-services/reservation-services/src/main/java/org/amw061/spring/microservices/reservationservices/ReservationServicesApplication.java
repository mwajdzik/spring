package org.amw061.spring.microservices.reservationservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.ImmutableList.of;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class ReservationServicesApplication {

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .groupName("Reservations")
                .select()
                .apis(basePackage(ReservationServicesApplication.class.getPackage().getName()))
                .paths(any())
                .build()
                .apiInfo(new ApiInfo("Reservation Services", "A set of services to provide data access to reservations", "1.0.0",
                        null, new Contact("MW", "https://amw061.org", null), null, null, of()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReservationServicesApplication.class, args);
    }
}
