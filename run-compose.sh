cd kogito-consumer/app
./mvnw clean package

cd ../../
cd kogito-producer/app
./mvnw clean package

cd ../../
sudo docker-compose build && sudo docker-compose up

