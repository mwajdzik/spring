
Config Server:
    git clone https://github.com/mwajdzik/configserver.git ~/Downloads/configserver
    check config with: http://localhost:8888/reservation-service/default
                       http://localhost:8888/reservation-client/default
                       http://localhost:8888/eureka-service/default
 

Eureka Registration Service:
    check: http://localhost:8761/
 

Hystrix Dashboard:
    check: http://localhost:8010/hystrix
    paste: http://localhost:9999/actuator/hystrix.stream
    see: https://dzone.com/articles/spring-cloud-with-turbine (to join multiple streams)
        

Reservation Service:
    check: registered at Eureka
    check: http://localhost:8000/reservations
    ckeck: http://localhost:8000/message

    change the message and refresh client's veriables with: 
        curl -X POST http://localhost:8000/actuator/refresh 

    start one more instance with -Dserver.port=8001


Reservation Client:
    an edge service, all the devices (mobiles, computers, ...) will connect to it
    
    Zuul - micro proxy
        http://localhost:8000/reservations                          - an actual service
        will be available at
        http://localhost:9999/reservation-service/reservations      - an edge service proxied with Zuul to the actual one
        reload - round-robin 8000/8001
        
    Feign is a declarative web service client.
    It makes writing web service clients easier. 
    To use Feign create an interface and annotate it.
        check: http://localhost:9999/reservations/names 
        
    Hysterix
        kill Reservation Service and check http://localhost:9999/reservations/names

    RabbitMQ
        brew services start rabbitmq
        check: http://localhost:15672

        Add a new name:
            curl -d'{"reservationName": "Ewa"}' -H'content-type: application/json' http://localhost:9999/reservations
        Kill the Reservation Service, add a few new names, check the queue, start Reservation Service and check if the names were added.


Distribute Tracing:

    TODO
    https://www.youtube.com/watch?v=5q8B6lYhFvE&t=3502s