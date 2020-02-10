package com.redhat.developer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redhat.developer.dto.DMNEvent;
import com.redhat.developer.dto.EvaluationResult;
import com.redhat.developer.grafana.GrafanaManager;
import com.redhat.developer.grafana.IGrafanaManager;
import com.redhat.developer.metrics.IMetricsCollector;
import com.redhat.developer.metrics.PrometheusMetricsCollector;
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

    @Inject
    IGrafanaManager grafanaManager;

    @Incoming("kogito-tracing")
    public void onProcessInstanceEvent(DMNEvent event) {
        LOGGER.info("Hey i've got this message: " + event.data.toString());
        CompletableFuture.runAsync(() -> {
            processEvent(event);
        });
    }

    private void processEvent(DMNEvent event){
        eventStorage.storeEvent(event.id, event);
        for(EvaluationResult result : event.data.results){
            grafanaManager.addNewRulePanel(result.name, result.evaluationStatus);
            PrometheusMetricsCollector.getCounter().labels("dmn", result.name, result.evaluationStatus).inc();
        }
    }
}
