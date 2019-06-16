FROM openjdk:12-jdk

VOLUME /tmp

EXPOSE 8080

COPY ./target/yamly-1.0-SNAPSHOT.jar /usr/app/

COPY ./wait-for-it.sh /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch yamly-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","yamly-1.0-SNAPSHOT.jar"]