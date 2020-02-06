package com.redhat.developer.tracing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.vertx.core.eventbus.EventBus;

@ApplicationScoped
public class TracingEventListenerFactory {

    @Inject
    EventBus bus;

    public TracingEventListener build() {
        return new TracingEventListener(bus);
    }

}
