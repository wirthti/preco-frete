FROM openjdk:21-jdk
#RUN apk add --no-cache tzdata
#RUN apk add --no-cache freetype
#RUN apk add --no-cache freetype-dev
ENV TZ=America/Sao_Paulo
WORKDIR /opt/app
#RUN addgroup -S spring && adduser -S spring -G spring
RUN groupadd -r spring && adduser -r spring -g spring
RUN mkdir /opt/app/logs
RUN touch /opt/app/logs/preco-frete.log
RUN chown -R spring:spring /opt/app/logs
USER spring:spring
VOLUME /tmp
COPY target/preco-frete.jar /opt/app/preco-frete.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/app/preco-frete.jar"]