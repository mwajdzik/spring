
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

        Add a new name: curl -d'{"reservationName": "Ewa"}' -H'content-type: application/json' http://localhost:9999/reservations
        Kill the Reservation Service, add a few new names, check the queue, start Reservation Service and check if the names were added.


Distribute Tracing with Sleuth (distributed tracing abstraction via logs)
    check: https://dzone.com/articles/microservices-part-6-distributed-tracing-with-spri
    
    [appname,traceId,spanId,exportable]
    Add a new name: curl -d'{"reservationName": "Ewa"}' -H'content-type: application/json' http://localhost:9999/reservations
    2018-06-11 20:01:50.016  INFO [reservation-client,c96c06c272d1477b,c96c06c272d1477b,false] 34091 --- [nio-9999-exec-1] o.a.s.r.ReservationApiGateway            : Writing reservation Reservation(reservationName=Ewa)
    2018-06-11 20:01:50.087  INFO [reservation-service,c96c06c272d1477b,6db9eb11e44e2ea7,false] 34019 --- [vations-group-1] o.a.s.reservation.ReservationProcessor   : New reservation Ewa saved


Distribute Tracing Platform Zipkin
