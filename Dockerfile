# Use Eclipse Temurin (OpenJDK 21)
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Make sure mvnw is executable (important on Windows)
RUN chmod +x mvnw

# Build the project without tests
RUN ./mvnw clean package -DskipTests

# Expose the app port
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "target/news-0.0.1-SNAPSHOT.jar"]
