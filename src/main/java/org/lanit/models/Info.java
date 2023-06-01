package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Info {

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setTickers(List<TickersItem> tickers) {
        this.tickers = tickers;
    }

    @JsonProperty("userID")
    private String userID;

    @JsonProperty("tickers")
    private List<TickersItem> tickers;

    public String getUserID() {
        return userID;
    }

    public List<TickersItem> getTickers() {
        return tickers;
    }
}