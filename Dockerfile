FROM gradle:jdk17

COPY --chown=gradle:gradle . /app

WORKDIR /app
RUN mkdir -p /project/item
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

EXPOSE 8080
RUN ls -al

CMD java -jar build/libs/demo-0.0.1-SNAPSHOT.jar