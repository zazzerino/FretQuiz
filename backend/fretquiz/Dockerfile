FROM docker.io/library/openjdk:16

COPY target/FretQuiz-1.0-SNAPSHOT.jar /app/FretQuiz.jar

EXPOSE 3000

CMD ["java", "-jar", "/app/FretQuiz.jar", "3000"]
