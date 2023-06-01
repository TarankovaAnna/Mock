


package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {


    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


    @JsonProperty("lastUpdate")
    private String lastUpdate;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("info")
    private Info info;


    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getUuid() {
        return uuid;
    }

    public Info getInfo() {
        return info;
    }
}