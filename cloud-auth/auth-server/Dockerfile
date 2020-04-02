FROM java:8
EXPOSE 8222
VOLUME /tmp
COPY target/auth.jar app.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","/app.jar"]