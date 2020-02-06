package com.redhat.developer.tracing;

import io.vertx.core.eventbus.EventBus;
import org.apache.commons.lang3.StringUtils;
import org.kie.dmn.api.core.*;
import org.kie.dmn.api.core.ast.DMNNode;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.event.*;
import org.kie.dmn.model.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class TracingEventListener implements DMNRuntimeEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(TracingEventListener.class);
    private static final int LOG_INDENT_SIZE = 4;

    private final EventBus bus;

    TracingEventListener(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void beforeEvaluateDecision(BeforeEvaluateDecisionEvent event) {
        LOG.info("TracingEventListener::beforeEvaluateDecision");
        log(event);
        send(tracingEventFrom(event));
    }

    @Override
    public void afterEvaluateDecision(AfterEvaluateDecisionEvent event) {
        LOG.info("TracingEventListener::afterEvaluateDecision");
        log(event);
        send(tracingEventFrom(event));
    }

    @Override
    public void beforeEvaluateBKM(BeforeEvaluateBKMEvent event) {
        LOG.info("TracingEventListener::beforeEvaluateBKM");
    }

    @Override
    public void afterEvaluateBKM(AfterEvaluateBKMEvent event) {
        LOG.info("TracingEventListener::afterEvaluateBKM");
    }

    @Override
    public void beforeEvaluateContextEntry(BeforeEvaluateContextEntryEvent event) {
        LOG.info("TracingEventListener::beforeEvaluateContextEntry");
    }

    @Override
    public void afterEvaluateContextEntry(AfterEvaluateContextEntryEvent event) {
        LOG.info("TracingEventListener::afterEvaluateContextEntry");
    }

    @Override
    public void beforeEvaluateDecisionTable(BeforeEvaluateDecisionTableEvent event) {
        LOG.info("TracingEventListener::beforeEvaluateDecisionTable");
    }

    @Override
    public void afterEvaluateDecisionTable(AfterEvaluateDecisionTableEvent event) {
        LOG.info("TracingEventListener::afterEvaluateDecisionTable");
    }

    @Override
    public void beforeEvaluateDecisionService(BeforeEvaluateDecisionServiceEvent event) {
        LOG.info("TracingEventListener::beforeEvaluateDecisionService");
    }

    @Override
    public void afterEvaluateDecisionService(AfterEvaluateDecisionServiceEvent event) {
        LOG.info("TracingEventListener::afterEvaluateDecisionService");
    }

    private void send(TracingEvent event) {
        bus.send("tracing", event);
    }

    private static TracingEvent tracingEventFrom(BeforeEvaluateDecisionEvent event) {
        return new TracingEvent(
                BeforeEvaluateDecisionEvent.class.getName(),
                event.getDecision().getModelName(),
                event.getDecision().getModelNamespace(),
                shallowCopy(event.getResult().getContext().getAll()),
                event.getResult().getDecisionResults().stream()
                        .map(TracingEventListener::mapDecisionResult)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    private static TracingEvent tracingEventFrom(AfterEvaluateDecisionEvent event) {
        return new TracingEvent(
                AfterEvaluateDecisionEvent.class.getName(),
                event.getDecision().getModelName(),
                event.getDecision().getModelNamespace(),
                shallowCopy(event.getResult().getContext().getAll()),
                event.getResult().getDecisionResults().stream()
                        .map(TracingEventListener::mapDecisionResult)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    private static TracingEvent.DecisionResult mapDecisionResult(DMNDecisionResult res) {
        return Optional.ofNullable(res).map(dr -> new TracingEvent.DecisionResult(
                dr.getDecisionId(),
                dr.getDecisionName(),
                dr.getEvaluationStatus(),
                dr.getResult(),
                dr.getMessages().stream()
                        .map(TracingEventListener::mapDecisionResultMessage)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        )).orElse(null);
    }

    private static TracingEvent.DecisionResult.Message mapDecisionResultMessage(DMNMessage msg) {
        return Optional.ofNullable(msg)
                .map(m -> new TracingEvent.DecisionResult.Message(
                        m.getMessage(),
                        m.getMessageType(),
                        m.getLevel(),
                        m.getSourceId()
                ))
                .orElse(null);
    }

    private static Map<String, Object> shallowCopy(Map<String, Object> original) {
        return new HashMap<>(original);
    }

    // ---- Logging utils

    private static void log(BeforeEvaluateDecisionEvent event) {
        log(0, event);
    }

    private static void log(int level, BeforeEvaluateDecisionEvent event) {
        logInd(level, "----- BeforeEvaluateDecisionEvent: BEGIN --------------------");
        logInd(level, "- decision:");
        log(level + 1, event.getDecision());
        logInd(level, "- result:");
        log(level + 1, event.getResult());
        logInd(level, "----- BeforeEvaluateDecisionEvent: END ----------------------");
    }

    private static void log(AfterEvaluateDecisionEvent event) {
        log(0, event);
    }

    private static void log(int level, AfterEvaluateDecisionEvent event) {
        logInd(level, "----- AfterEvaluateDecisionEvent: BEGIN ---------------------");
        logInd(level, "- decision:");
        log(level + 1, event.getDecision());
        logInd(level, "- result:");
        log(level + 1, event.getResult());
        logInd(level, "----- AfterEvaluateDecisionEvent: END -----------------------");
    }

    private static void log(int level, DecisionNode node) {
        log_DMNNode(level, node);
        logInd(level, "- resultType:");
        log(level + 1, node.getResultType());
        logInd(level, "- decision:");
        log(level + 1, node.getDecision());
    }

    private static void log_DMNNode(int level, DMNNode node) {
        logInd(level, "- id: {}", node.getId());
        logInd(level, "- name: {}", node.getName());
        logInd(level, "- modelName: {}", node.getModelName());
        logInd(level, "- modelNamespace: {}", node.getModelNamespace());
    }

    private static void log(int level, Decision decision) {
        logInd(level, "- id: {}", decision.getId());
        logInd(level, "- name: {}", decision.getName());
        logInd(level, "- description: {}", decision.getDescription());
        logInd(level, "- identifierString: {}", decision.getIdentifierString());
        logInd(level, "- question: {}", decision.getQuestion());
        logInd(level, "- allowedAnswers: {}", decision.getAllowedAnswers());
        if (decision.getExpression() != null) {
            logInd(level, "- expression:");
            log(level + 1, decision.getExpression());
        }
        if (decision.getVariable() != null) {
            logInd(level, "- variable:");
            log(level + 1, decision.getVariable());
        }
        logList(level, decision.getInformationRequirement(), "informationRequirement", TracingEventListener::log);
        logList(level, decision.getDecisionOwner(), "decisionOwner", TracingEventListener::log);
        logList(level, decision.getDecisionMaker(), "decisionMaker", TracingEventListener::log);
        logList(level, decision.getImpactedPerformanceIndicator(), "impactedPerformaceIndicator", TracingEventListener::log);
        logList(level, decision.getSupportedObjective(), "supportedObjective", TracingEventListener::log);
    }

    private static void log(int level, InformationRequirement req) {
        if (req.getRequiredInput() != null) {
            logInd(level, "- requiredInput:");
            log(level + 1, req.getRequiredInput());
        }
        if (req.getRequiredDecision() != null) {
            logInd(level, "- requiredDecision:");
            log(level + 1, req.getRequiredDecision());
        }
    }

    private static void log(int level, Expression exp) {
        log_DMNElement(level, exp);
        logInd(level, "- typeRef: {}", exp.getTypeRef());
    }

    private static void log(int level, InformationItem inf) {
        log_DMNElement(level, inf);
        logInd(level, "- name: {}", inf.getName());
        logInd(level, "- typeRef: {}", inf.getTypeRef());
    }

    private static void log_DMNElement(int level, DMNElement exp) {
        logInd(level, "- id: {}", exp.getId());
        logInd(level, "- label: {}", exp.getLabel());
        logInd(level, "- description: {}", exp.getDescription());
    }

    private static void log(int level, DMNElementReference ref) {
        logInd(level, "- identifierString: {}", ref.getIdentifierString());
        logInd(level, "- href: {}", ref.getHref());
    }

    private static void log(int level, DMNType type) {
        logInd(level, "- id: {}", type.getId());
        logInd(level, "- name: {}", type.getName());
        logInd(level, "- namespace: {}", type.getNamespace());
        logMap(level, type.getFields(), "fields", TracingEventListener::log);
    }

    private static void log(int level, DMNResult result) {
        logInd(level, "- context:");
        log(level + 1, result.getContext());
        logList(level, result.getDecisionResults(), "decisionResults", TracingEventListener::log);
        log_DMNMessageContainer(level, result);
    }

    private static void log(int level, DMNDecisionResult dr) {
        logInd(level, "- decisionId: {}", dr.getDecisionId());
        logInd(level, "- decisionName: {}", dr.getDecisionName());
        logInd(level, "- evaluationStatus: {}", dr.getEvaluationStatus());
        logInd(level, "- result: {}", dr.getResult());
        logList(level, dr.getMessages(), "messages", TracingEventListener::log);
    }

    private static void log_DMNMessageContainer(int level, DMNMessageContainer container) {
        logInd(level, "- errors: {}", container.hasErrors());
        logList(level, container.getMessages(), "messages", TracingEventListener::log);
    }

    private static void log(int level, DMNMessage msg) {
        logInd(level, "- id: {}", msg.getId());
        logInd(level, "- type: {}", msg.getMessageType());
        logInd(level, "- text: {}", msg.getText());
        logInd(level, "- level: {}", msg.getLevel());
    }

    private static void log(int level, DMNContext ctx) {
        if (ctx.getAll() != null && !ctx.getAll().isEmpty()) {
            ctx.getAll().forEach((name, obj) -> {
                logInd(level, "- \"{}\": {}", name, io.cloudevents.json.Json.encode(obj));
            });
        } else {
            logInd(level, "<EMPTY>");
        }
    }

    private static <T> void logList(int level, List<T> list, String header, BiConsumer<Integer, T> logElement) {
        if (list != null && !list.isEmpty()) {
            logInd(level, "- {}:", header);
            for (int i = 0; i < list.size(); i++) {
                logInd(level + 1, "[{}]:", i + 1);
                logElement.accept(level + 2, list.get(i));
            }
        }
    }

    private static <T> void logMap(int level, Map<String, T> map, String header, BiConsumer<Integer, T> logElement) {
        if (map != null && !map.isEmpty()) {
            logInd(level, "- {}:", header);
            map.forEach((key, value) -> {
                logInd(level + 1, "- \"{}\":", key);
                logElement.accept(level + 2, value);
            });
        }
    }

    private static void logInd(int level, String str) {
        LOG.info(StringUtils.repeat(' ', level * 4) + str);
    }

    private static void logInd(int level, String str, Object... objs) {
        LOG.info(StringUtils.repeat(' ', level * 4) + str, objs);
    }

}
