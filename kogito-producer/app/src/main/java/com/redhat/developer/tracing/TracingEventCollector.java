package com.redhat.developer.tracing;

import io.cloudevents.v1.CloudEventBuilder;
import io.cloudevents.v1.CloudEventImpl;
import io.quarkus.vertx.ConsumeEvent;
import io.reactivex.BackpressureStrategy;
import io.reactivex.subjects.PublishSubject;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class TracingEventCollector {

    private static final Logger LOG = LoggerFactory.getLogger(TracingEventCollector.class);

    private final PublishSubject<String> eventSubject = PublishSubject.create();

    @ConsumeEvent("tracing")
    public String handleEvent(TracingEvent event) {
        try {
            LOG.trace("TracingEventCollector::handleEvent");

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("context", event.getContext());
            dataMap.put("results", event.getResults());

            CloudEventImpl<Map<String, Object>> cloudEvent =
                    CloudEventBuilder.<Map<String, Object>>builder()
                            .withType(event.getType())
                            .withId(UUID.randomUUID().toString())
                            .withSource(URI.create(String.format("%s/%s",
                                                                 event.getModelNamespace(),
                                                                 urlEncode(event.getModelName())
                            )))
                            .withData(dataMap)
                            .build();

            String payload = io.cloudevents.json.Json.encode(cloudEvent);

            LOG.trace("Payload: {}", payload);

            eventSubject.onNext(payload);
            return cloudEvent.getAttributes().getId();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    @Outgoing("kogito-tracing")
    public Publisher<String> getEventPublisher() {
        return eventSubject.toFlowable(BackpressureStrategy.BUFFER);
    }

    private static String urlEncode(String input) {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
