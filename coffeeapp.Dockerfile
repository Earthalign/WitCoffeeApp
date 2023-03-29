FROM openjdk:19-jdk-alpine AS builder

COPY . .
RUN apk add maven && \
    mvn clean install && \
    ./mvnw package && \
    mv target/*.jar coffeapp.jar

FROM openjdk:19-jdk-alpine AS runner
WORKDIR /coffeeapp
COPY --from=builder initDB.mv.db .
COPY --from=builder coffeapp.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar"]
CMD ["coffeapp.jar"]
