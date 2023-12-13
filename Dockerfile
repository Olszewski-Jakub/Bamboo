FROM openjdk:20-jdk-slim

# Set the working directory in the image
WORKDIR /app

# Copy gradle executable to the image
COPY gradlew .
COPY gradle gradle
COPY src/main/resources/firebase_config.json src/main/resources/firebase_config.json
# Grant permission to execute the gradlew script
RUN chmod +x ./gradlew

# Copy the rest of the project to the image
COPY . .

# Build the project inside the docker image
RUN ./gradlew build -x test


# Add Maintainer Info
LABEL maintainer="Olszewski-Jakub"

# The application's port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/build/libs/bamboo.jar"]
