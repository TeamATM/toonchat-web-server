FROM amazoncorretto:17

RUN curl -Lo dd-java-agent.jar https://dtdg.co/latest-java-tracer

COPY ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]