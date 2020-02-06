package com.redhat.developer.grafana;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redhat.developer.grafana.model.GrafanaDashboard;
import com.redhat.developer.responses.GrafanaNewDashboardResponse;

public interface IGrafanaManager {
    void createDefaultDashboard();

    GrafanaNewDashboardResponse createNewDashboard(GrafanaDashboard req) throws JsonProcessingException;

    boolean addNewRulePanel(String ruleName, String status);

    String getCurrentConfiguration();
}
