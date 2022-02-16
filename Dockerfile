FROM hub.c.163.com/library/java:8-alpine

MAINTAINER xinxin 377241804@qq.com

ADD target/*.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "/app.jar"]