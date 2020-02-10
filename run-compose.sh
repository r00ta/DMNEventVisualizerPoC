cd kogito-consumer/app
./mvnw clean package

cd ../../
cd kogito-producer/app
./mvnw clean package

cd ../../
sudo docker-compose -f docker-compose-elastic.yml build && sudo docker-compose -f docker-compose-elastic.yml up

