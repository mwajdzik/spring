
Config Server:
    git clone https://github.com/mwajdzik/configserver.git ~/Downloads/configserver
    check config with: http://localhost:8888/reservation-service/default
 
Reservation Service:
    run with active profile of 'reservationsystem'

    check: http://localhost:8000/reservations
    ckeck: http://localhost:8000/message

    change the message and refresh client's veriables with: 
        curl -X POST http://localhost:8000/actuator/refresh 
