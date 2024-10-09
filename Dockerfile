# the base image
FROM eclipse-temurin:17-jdk-focal

# the JAR file path
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context into the Docker image
COPY ${JAR_FILE} payment-0.1.jar

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx1024M", "-jar", "/payment-0.1.jar"]
