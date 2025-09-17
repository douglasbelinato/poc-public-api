FROM maven:3.9.9-amazoncorretto-21-debian AS build
 
RUN ln -sf /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime \
&& dpkg-reconfigure -f noninteractive tzdata
 
WORKDIR /app

COPY /src  /app/src
COPY pom.xml /app

RUN mvn clean package -DskipTests

FROM gcr.io/distroless/java21-debian12:nonroot
 
COPY --from=build /etc/localtime /etc/localtime
COPY --from=build /etc/timezone /etc/timezone
COPY --from=build /app/target/poc-public-app*.jar poc-public-app.jar
 
EXPOSE 8080
CMD ["-XX:MaxRAMPercentage=75","-XX:+UseParallelGC", "poc-public-app.jar"]
