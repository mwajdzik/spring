package org.amw061.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
    Adds 'refresh' scope of Spring beans - @RefreshScope
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

/*
    LOCAL CONFIG:
        application.properties:
            spring.profiles.active=native


    GIT BASED:
        application.properties:
            spring.cloud.config.server.git.uri=https://github.com/mwajdzik/spring-cloud-config-repo


    http://localhost:8888/discovery-server/default
    http://localhost:8888/eureka-client/default
    http://localhost:8888/eureka-service/default
 */