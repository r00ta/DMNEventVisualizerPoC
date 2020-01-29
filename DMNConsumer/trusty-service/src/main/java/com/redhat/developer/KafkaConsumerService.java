package com.redhat.developer;

import java.time.Duration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.developer.domain.DMNResult;
import io.quarkus.runtime.StartupEvent;
import com.redhat.developer.kafka.KafkaConsumerFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaConsumerService implements IKafkaConsumerService {

    @Inject
    IDecisionStorageService storageService;

    final static String topicName = "test-topic";
    private ObjectMapper objectMapper = new ObjectMapper();

    private int counter = 0;

    // TODO: Find another way to start the application, because even if mocked the CDI will start it!
    void onStart(@Observes StartupEvent event) {

//
//        if (true){ // TRUE if you want to run tests -> TO BE FIXED
//            return;
//        }

        KafkaConsumer consumer = KafkaConsumerFactory.CreateWithDefault(topicName);

        //print the topic name
        System.out.println("Subscribed to topic " + topicName);
        int i = 0;
        DMNResult result;

        while (true) {
            System.out.println("Querying kafka");
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
            System.out.println("Exit");
            for (ConsumerRecord<String, String> record : records) {
                try {
                    result = objectMapper.readValue(record.value(), DMNResult.class);
                } catch (JsonProcessingException e) {
                    System.out.println("Got a non valid event");
                    e.printStackTrace();
                    continue;
                }
                storageService.storeDMNResult(record.key(), result);
                counter++;
                // print the offset,key and value for the consumer records.
                System.out.printf("offset = %d, key = %s, #events = %d",
                                  record.offset(), record.key(), counter);
            }
        }
    }

    public String getEventCount(){
        return String.valueOf(counter);
    }
}

