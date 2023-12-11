# Use an official OpenJDK runtime as a parent image
# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17
RUN microdnf install findutils
# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Grant execute permission to the gradlew script
RUN chmod +x ./gradlew

# Grant execute permission to the gradlew script
RUN chmod +x ./gradlew

# Run Gradle build to compile and package the application
RUN ./gradlew build

# Expose the port the app runs on
EXPOSE 8080

# Define environment variable
ENV APP_NAME=bamboo

# Run the JAR file
CMD ["java", "-jar", "build/libs/${APP_NAME}.jar"]