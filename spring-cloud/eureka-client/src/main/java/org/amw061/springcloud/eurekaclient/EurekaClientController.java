package org.amw061.springcloud.eurekaclient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

// for refreshing scope from the config server - Actuator adds an endpoint to refresh the scope
// POST: localhost:8090/refresh

// second option is to use Spring Cloud Bus to broadcast the change to all instances

@RefreshScope
@RestController
public class EurekaClientController {

    @Resource
    private EurekaClient eurekaClient;

    // the alternative way of getting access to a server
    // @Resource private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplateBuilder builder;

    @Value("${client-name}")
    private String clientName;

    @RequestMapping("/")
    public String callService() {
        // List<ServiceInstance> instances = discoveryClient.getInstances("service");
        // URI uri = instances.get(0).getUri();

        RestTemplate restTemplate = builder.build();
        InstanceInfo server = eurekaClient.getNextServerFromEureka("EUREKA-SERVICE", false);

        String baseUrl = server.getHomePageUrl();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);

        return "<b>From Config Server:</b> " + clientName + "<br>" +
                "<b>From Eureka Service:</b> " + response.getBody();
    }
}
