package com.redhat.developer.grafana.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrafanaDashboard {
    @JsonProperty("dashboard")
    public GrafanaDashboardMeta meta;

    @JsonProperty("folderId")
    public int folderId;

    @JsonProperty("overwrite")
    public boolean overwrite;

    public GrafanaDashboard(int folderId, boolean overwrite){
        this.folderId = folderId;
        this.overwrite = overwrite;
    }
}
