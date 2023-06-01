package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Add {

    public Add(String name, int percent, int timeFrame) {
        this.name = name;
        this.percent = percent;
        this.timeFrame = timeFrame;
    }

    @JsonProperty("name")
    private String name;

    @JsonProperty("percent")
    private int percent;

    @JsonProperty("timeFrame")
    private int timeFrame;

    public String getName() {
        return name;
    }

    public int getPercent() {
        return percent;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }
}