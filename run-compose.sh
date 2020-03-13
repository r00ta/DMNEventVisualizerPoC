cd kogito-consumer/app
./mvnw clean package

cd ../../
cd loanEligibilityApp/app
./mvnw clean package
cp target/generated-sources/kogito/dashboards/* ../../grafana/provisioning/dashboards

cd ../../
sudo docker-compose build && sudo docker-compose up

