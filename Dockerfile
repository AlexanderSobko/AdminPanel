FROM maven:3-openjdk-17-slim AS MAVEN_BUILD

ARG URL
ARG USERNAME
ARG PASSWORD

COPY ./ ./

RUN mvn clean install -DDB_URL=$URL -DDB_USERNAME=$USERNAME -DDB_PASSWORD=$PASSWORD

FROM eclipse-temurin:17-jre-alpine

ARG URL
ARG USERNAME
ARG PASSWORD
ARG SECRET

ENV DB_URL=$URL
ENV DB_USERNAME=$USERNAME
ENV DB_PASSWORD=$PASSWORD
ENV JWT_SECRET=$SECRET

EXPOSE 3001

COPY --from=MAVEN_BUILD /target/*.jar /admin.jar

CMD ["java", "-jar", "/admin.jar"]