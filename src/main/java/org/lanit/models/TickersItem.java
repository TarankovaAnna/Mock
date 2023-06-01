package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TickersItem {

    public void setAlerts(List<AlertsItem> alerts) {
        this.alerts = alerts;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @JsonProperty("alerts")
    private List<AlertsItem> alerts;

    @JsonProperty("ticker")
    private String ticker;

    public List<AlertsItem> getAlerts() {
        return alerts;
    }

    public String getTicker() {
        return ticker;
    }
}