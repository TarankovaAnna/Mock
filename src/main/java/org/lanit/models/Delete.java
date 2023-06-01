package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Delete {

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    public void setAlertIndex(int alertIndex) {
        this.alertIndex = alertIndex;
    }

    @JsonProperty("tickerName")
    private String tickerName;

    @JsonProperty("alertIndex")
    private int alertIndex;

    public String getTickerName() {
        return tickerName;
    }

    public int getAlertIndex() {
        return alertIndex;
    }
}