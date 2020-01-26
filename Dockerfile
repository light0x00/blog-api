FROM maven:3.6-jdk-8-alpine AS builder
COPY . /app
WORKDIR /app
RUN mvn clean install -U -P pro -V -Dmaven.javadoc.skip=true -DskipTests=true

FROM openjdk:8-jre-alpine
COPY --from=builder /app/blog-web/target/blog-api.jar /
ENV DB_PWD=
ENV DB_USER=blog
ENV EMAIL_PWD=
EXPOSE 8080
CMD ["java","-jar","blog-api.jar"]
