FROM maven:3.3-jdk-8
COPY . /
CMD ["mvn", "package", "exec:java", "\"-Dexec.args=test-topic\""]

