package com.redhat.developer.grafana;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.developer.grafana.model.GrafanaDashboard;
import com.redhat.developer.grafana.model.GrafanaDashboardMeta;
import com.redhat.developer.grafana.model.panel.GrafanaGridPos;
import com.redhat.developer.grafana.model.panel.GrafanaPanel;
import com.redhat.developer.grafana.model.panel.GrafanaTarget;
import com.redhat.developer.responses.GrafanaNewDashboardResponse;
import com.redhat.developer.utils.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class GrafanaManager implements IGrafanaManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    private Set<String> activatedRules;

    private HttpHelper httpHelper;

    private static final String BASEHOST = "http://grafana:3000";

    private ObjectMapper mapper = new ObjectMapper();

    private GrafanaDashboard currentDashboard;

    private int numOfPanel = 0;

    @PostConstruct
    public void setUp() throws JsonProcessingException {
        activatedRules = new HashSet<String>();
        httpHelper = new HttpHelper(BASEHOST);
        GrafanaDashboard dashboard = new GrafanaDashboard(0, true);
        dashboard.meta = new GrafanaDashboardMeta(null, null, "DRL Dashboard", null, "browser", 16, 0);
        GrafanaNewDashboardResponse response = createNewDashboard(dashboard);
        currentDashboard = dashboard;
        LOGGER.info("Grafana dashboard has been created.");
    }

    @Override
    public void createDefaultDashboard() {
        httpHelper.doPost("todo", null);
    }

    @Override
    public GrafanaNewDashboardResponse createNewDashboard(GrafanaDashboard req) throws JsonProcessingException {
        String param = mapper.writeValueAsString(req);
        currentDashboard = req;
        return mapper.readValue(httpHelper.doPost("/api/dashboards/db", param), GrafanaNewDashboardResponse.class);
    }

    @Override
    public String getCurrentConfiguration() {
        return httpHelper.doGet("/api/dashboards/home");
    }

    @Override
    public synchronized boolean addNewRulePanel(String ruleName, String status) {
        if (!activatedRules.contains(ruleName)){
            LOGGER.info("New rule: " + ruleName);
            numOfPanel++;
            activatedRules.add(ruleName);
            GrafanaPanel panel = new GrafanaPanel(
                    new Random().nextInt(),
                    ruleName + " fire count",
                    "graph"
            );
            panel.gridPos = new GrafanaGridPos(12 * ( (numOfPanel - 1) % 2), 8 * ((numOfPanel - 1) / 2), 12, 8);
            panel.targets.add(new GrafanaTarget(String.format("dmn_rules{rule_name=\"%s\"}", ruleName), "time_series", 1, ruleName));
            panel.lines = true;
            currentDashboard.meta.panels.add(panel);
            try {
                createNewDashboard(currentDashboard);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            LOGGER.info("New grafana panel has been added.");
        }
        return true;
    }
}
