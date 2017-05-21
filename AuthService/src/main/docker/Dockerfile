FROM java:8
VOLUME /tmp
ADD authservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
CMD java $JAVA_OPTIONS -jar app.jar