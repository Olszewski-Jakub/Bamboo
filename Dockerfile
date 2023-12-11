# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Run Gradle build to compile and package the application
RUN ./gradlew clean build

# Expose the port the app runs on
EXPOSE 8080

# Define environment variable
ENV APP_NAME=bamboo

# Run the JAR file
CMD ["java", "-jar", "build/libs/${APP_NAME}.jar"]
