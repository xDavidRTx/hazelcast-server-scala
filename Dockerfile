FROM hseeberger/scala-sbt:8u212_1.2.8_2.12.8

COPY . .

ENV VERTICLE_HOME /usr/verticles
ENV VERTICLE_FILE hazelcast-server-0.0.1.jar

RUN sbt clean assembly

RUN mkdir -p $VERTICLE_HOME
RUN cp target/$VERTICLE_FILE $VERTICLE_HOME/$VERTICLE_FILE

WORKDIR $VERTICLE_HOME

CMD ["java", "-jar", "hazelcast-server-0.0.1.jar", "-cluster" ,"-Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory"]
