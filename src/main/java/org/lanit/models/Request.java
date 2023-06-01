package org.lanit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    public void setAdd(Add add) {
        this.add = add;
    }

    @JsonProperty("add")
    private Add add;

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setDelete(Delete delete) {
        this.delete = delete;
    }

    public Delete getDelete() {
        return delete;
    }

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("info")
    private Info info;

    @JsonProperty("delete")
    private Delete delete;

    public Add getAdd() {
        return add;
    }

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