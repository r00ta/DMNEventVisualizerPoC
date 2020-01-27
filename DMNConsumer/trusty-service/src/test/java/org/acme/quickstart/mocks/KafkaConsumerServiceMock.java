package org.acme.quickstart.mocks;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.Mock;
import org.acme.quickstart.IKafkaConsumerService;
import org.acme.quickstart.KafkaConsumerService;

@Mock
@Singleton
public class KafkaConsumerServiceMock implements IKafkaConsumerService {

    @Override
    public String getEventCount() {
        System.out.println("fsdiojfsdiojfd");
        return "0";
    }
}
