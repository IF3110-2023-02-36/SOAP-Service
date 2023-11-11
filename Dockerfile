FROM maven:3-amazoncorretto-8 as build

WORKDIR /build
COPY . .
RUN mvn clean install assembly:single


FROM amazoncorretto:8

WORKDIR /soap
COPY --from=build /build/target/soap-service-jar-with-dependencies.jar .
EXPOSE 6001
ENTRYPOINT ["java", "-jar", "soap-service-jar-with-dependencies.jar"]