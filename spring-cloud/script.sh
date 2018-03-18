#!/bin/bash

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home
PATH=$JAVA_HOME/bin:$PATH

##############

cd config-server
mvn --quiet install
cd target

java -jar config-server-0.0.1.jar &
cd ../..

open http://localhost:8888/eureka-client/default
open http://localhost:8888/eureka-service/default

##############

cd discovery-server
mvn --quiet install
cd target

java -jar discovery-server-0.0.1.jar &
open http://localhost:8761/ &
cd ../..

###############

cd eureka-service
mvn --quiet install
cd target

java -jar -Dserver.port=8081 -Dservice.instance.name="Instance #1" eureka-service-0.0.1.jar &
java -jar -Dserver.port=8082 -Dservice.instance.name="Instance #2" eureka-service-0.0.1.jar &
java -jar -Dserver.port=8083 -Dservice.instance.name="Instance #3" eureka-service-0.0.1.jar &

open http://localhost:8081/ &
open http://localhost:8082/ &
open http://localhost:8083/ &
cd ../..

###############

cd eureka-client
mvn --quiet install
cd target

java -jar -Dserver.port=8091 -Dspring.profiles.active=dev eureka-client-0.0.1.jar &
java -jar -Dserver.port=8092 -Dspring.profiles.active=qa eureka-client-0.0.1.jar &
java -jar -Dserver.port=8093 -Dspring.profiles.active=default eureka-client-0.0.1.jar &

open http://localhost:8091/ &
open http://localhost:8092/ &
open http://localhost:8093/ &
cd ../..
