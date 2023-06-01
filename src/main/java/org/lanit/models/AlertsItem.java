package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlertsItem {

    public void setTimeframe(int timeframe) {
        this.timeframe = timeframe;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @JsonProperty("timeframe")
    private int timeframe;

    @JsonProperty("percent")
    private int percent;

    public int getTimeframe() {
        return timeframe;
    }

    public int getPercent() {
        return percent;
    }
}