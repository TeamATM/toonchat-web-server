FROM amazoncorretto:17

RUN curl -o /dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'

ARG jarFile=./build/libs/*.jar

COPY $jarFile app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]