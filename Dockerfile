FROM amd64/amazoncorretto:17
ARG JAR_FILE=./build/libs/gcp-0.0.1-SNAPSHOT.jar
COPY ./build/libs/3rd-sc-ex1-CitySavior-Backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]
