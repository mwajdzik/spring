package org.amw061.spring.microservices.roomservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.ImmutableList.of;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class RoomServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomServicesApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .groupName("Room")
                .select()
                .apis(basePackage(RoomServicesApplication.class.getPackage().getName()))
                .paths(any())
                .build()
                .apiInfo(new ApiInfo("Room Services", "A set of services to provide data access to rooms", "1.0.0",
                        null, new Contact("Frank Moley", "https://twitter.com/fpmoles", null), null, null, of()));
    }
}
