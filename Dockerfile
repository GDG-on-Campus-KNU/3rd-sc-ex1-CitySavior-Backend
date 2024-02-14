FROM amd64/amazoncorretto:17
ARG JAR_FILE=./build/libs/CitySavior-0.0.1-SNAPSHOT.jar
COPY ./build/libs/CitySavior-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]
