package com.redhat.developer.metrics;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface IMetricsCollector {
    boolean registerValue(String key, Object value);
}
