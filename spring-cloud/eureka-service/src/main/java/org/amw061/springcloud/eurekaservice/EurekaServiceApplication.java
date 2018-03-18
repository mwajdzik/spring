package org.amw061.springcloud.eurekaservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class EurekaServiceApplication {

    // from command line param
    @Value("${service.instance.name}")
    private String instance;

    // from application.properties
    @Value("${spring.application.name}")
    private String appName;

    // from GIT eureka-service.properties
    @Value("${service.message}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }

    @RequestMapping("/")
    public String message() {
        return message + " '" + appName + "/" + instance + "' @" + now();
    }
}
