FROM adoptopenjdk/openjdk17:alpine-jre

WORKDIR /app

COPY . .

RUN ./gradlew build

RUN jar -xf build/libs/bamboo-0.0.1-SNAPSHOT.jar

RUN tar -cf artifact.tar META-INF/ BOOT-INF/ classes/

FROM alpine:latest

WORKDIR /artifact

COPY --from=0 /app/artifact.tar .

RUN tar -xf artifact.tar

EXPOSE 8080

CMD ["java", "-cp", "classes:META-INF/MANIFEST.MF", "com.example.MyApp"]