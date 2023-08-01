FROM amazoncorretto:17

ARG jarFile=./build/libs/*.jar

COPY $jarFile app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]