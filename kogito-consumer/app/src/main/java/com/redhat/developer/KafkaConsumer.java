package com.redhat.developer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.developer.dto.DMNEvent;
import com.redhat.developer.storage.IEventStorage;
import com.redhat.developer.utils.HttpHelper;
import com.redhat.developer.utils.JsonUtils;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    @Inject
    IEventStorage eventStorage;

    @Incoming("kogito-tracing")
    public void onProcessInstanceEvent(DMNEvent event) {
        LOGGER.info("Hey i've got this message: " + event.data.toString());
        CompletableFuture.runAsync(() -> {
            processEvent(event);
        });
    }

    private void processEvent(DMNEvent event){
        eventStorage.storeEvent(event.id, event);
    }
}
