FROM openjdk:14-slim

ADD target/todo-srv.jar /usr/local/todo/todo-srv.jar
ENTRYPOINT ["java", "-jar", "/usr/local/todo/todo-srv.jar"]