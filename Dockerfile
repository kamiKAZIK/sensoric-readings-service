FROM java:alpine
RUN mkdir -p /usr/local/applications && \
    sed -i "s/\(securerandom\.source=\).*\$/\1file\:\/dev\/urandom/" $JAVA_HOME/jre/lib/security/java.security
WORKDIR /usr/local/applications
ARG JAR_FILE
ADD ${JAR_FILE} sensoric-readings-service.jar
EXPOSE 38888
ENTRYPOINT ["java", "-jar", "-Xmx96m", "-Xss512k", "sensoric-readings-service.jar"]
