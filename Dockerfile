FROM amd64/amazoncorretto:17
COPY ./build/libs/3rd-sc-ex1-CitySavior-Backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]