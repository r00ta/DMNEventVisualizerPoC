package com.redhat.developer.metrics;

import io.prometheus.client.Counter;

public class PrometheusMetricsCollector implements IMetricsCollector {

    protected static final Counter exitStatus = Counter.build()
            .name("dmn_rules")
            .help("Rule process result")
            .labelNames("identifier", "rule_name", "status")
            .register();

    public static Counter getCounter() {
        return exitStatus;
    }

    @Override
    public boolean registerValue(String key, Object value) {
        return false;
    }
}
