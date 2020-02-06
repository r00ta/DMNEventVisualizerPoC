package com.redhat.developer;

import org.kie.dmn.api.core.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugListener implements DMNRuntimeEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(DebugListener.class);

    @Override
    public void beforeEvaluateDecision(BeforeEvaluateDecisionEvent event) {
        LOG.info("DebugListener::beforeEvaluateDecision");
    }

    @Override
    public void afterEvaluateDecision(AfterEvaluateDecisionEvent event) {
        LOG.info("DebugListener::afterEvaluateDecision");
    }

    @Override
    public void beforeEvaluateBKM(BeforeEvaluateBKMEvent event) {
        LOG.info("DebugListener::beforeEvaluateBKM");
    }

    @Override
    public void afterEvaluateBKM(AfterEvaluateBKMEvent event) {
        LOG.info("DebugListener::afterEvaluateBKM");
    }

    @Override
    public void beforeEvaluateContextEntry(BeforeEvaluateContextEntryEvent event) {
        LOG.info("DebugListener::beforeEvaluateContextEntry");
    }

    @Override
    public void afterEvaluateContextEntry(AfterEvaluateContextEntryEvent event) {
        LOG.info("DebugListener::afterEvaluateContextEntry");
    }

    @Override
    public void beforeEvaluateDecisionTable(BeforeEvaluateDecisionTableEvent event) {
        LOG.info("DebugListener::beforeEvaluateDecisionTable");
    }

    @Override
    public void afterEvaluateDecisionTable(AfterEvaluateDecisionTableEvent event) {
        LOG.info("DebugListener::afterEvaluateDecisionTable");
    }

    @Override
    public void beforeEvaluateDecisionService(BeforeEvaluateDecisionServiceEvent event) {
        LOG.info("DebugListener::beforeEvaluateDecisionService");
    }

    @Override
    public void afterEvaluateDecisionService(AfterEvaluateDecisionServiceEvent event) {
        LOG.info("DebugListener::afterEvaluateDecisionService");
    }
}
