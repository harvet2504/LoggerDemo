FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine-slim
  
LABEL system="demo" \
    name="logger" \
    mantainer="hgonzalez@soaint.com" \

RUN apk add --update tzdata
ENV TZ=America/Lima
COPY ./build/libs/*.jar app.jar
EXPOSE 7104
ENTRYPOINT ["java","-jar","/app.jar"]
