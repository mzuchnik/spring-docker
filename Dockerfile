FROM openjdk:14 as builder
WORKDIR application
COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ["chmod", "+x", "mvnw"]
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw clean package && cp target/spring-docker-0.0.1-SNAPSHOT.jar spring-docker-0.0.1-SNAPSHOT.jar
RUN java -Djarmode=layertools -jar spring-docker-0.0.1-SNAPSHOT.jar extract


FROM openjdk:14
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]