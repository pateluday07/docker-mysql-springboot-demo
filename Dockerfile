FROM openjdk:11.0.9.1

MAINTAINER pateluday07@gmail.com

VOLUME /tmp

COPY /target/docker-mysql-springboot-demo-0.0.1-SNAPSHOT.jar docker-mysql-springboot-demo-0.0.1-SNAPSHOT.jar

COPY wait-for-mysql.sh /wait-for-mysql.sh

RUN chmod +x /wait-for-mysql.sh

ENTRYPOINT ["/wait-for-mysql.sh"]

CMD ["java","-jar","/docker-mysql-springboot-demo-0.0.1-SNAPSHOT.jar"]