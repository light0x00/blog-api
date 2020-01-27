FROM maven:3.6-jdk-8-alpine AS builder
WORKDIR /app
COPY . /app
RUN mvn install -P pro -V -Dmaven.javadoc.skip=true -DskipTests=true --settings .mvn/settings.xml

FROM openjdk:8-jre-alpine
COPY --from=builder /app/blog-web/target/blog-api.jar /
ENV DB_PWD=
ENV DB_USER=blog
ENV EMAIL_PWD=
EXPOSE 8080
CMD ["java","-Xmx128m","-jar","blog-api.jar"]
