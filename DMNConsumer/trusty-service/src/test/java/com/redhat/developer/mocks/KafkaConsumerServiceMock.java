package com.redhat.developer.mocks;

import javax.inject.Singleton;

import io.quarkus.test.Mock;
import com.redhat.developer.IKafkaConsumerService;

@Mock
@Singleton
public class KafkaConsumerServiceMock implements IKafkaConsumerService {

    @Override
    public String getEventCount() {
        System.out.println("fsdiojfsdiojfd");
        return "0";
    }
}
