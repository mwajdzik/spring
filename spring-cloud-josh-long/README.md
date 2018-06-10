
Config Server:
    git clone https://github.com/mwajdzik/configserver.git ~/Downloads/configserver
    check config with: http://localhost:8888/reservation-service/default
                       http://localhost:8888/reservation-client/default
                       http://localhost:8888/eureka-service/default
 

Eureka Registration Service:
    check: http://localhost:8761/
    

Reservation Service:
    check: registered at Eureka
    check: http://localhost:8000/reservations
    ckeck: http://localhost:8000/message

    change the message and refresh client's veriables with: 
        curl -X POST http://localhost:8000/actuator/refresh 


Reservation Client:
    an edge service, all the devices (mobiles, computers, ...) will connect to it
    
    Zuul - micro proxy
        http://localhost:8000/reservations                          - an actual service
        will be available at
        http://localhost:9999/reservation-service/reservations      - an edge service proxied with Zuul to the actual one