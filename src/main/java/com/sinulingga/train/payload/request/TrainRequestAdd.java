package com.sinulingga.train.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrainRequestAdd {
    @JsonProperty(namespace = "name",required = true)
    String name;
    @JsonProperty(namespace = "description", required = false)
    String description;
    @JsonProperty(namespace = "code", required = true)
    String code;
    @JsonProperty(namespace = "capacity", required = true)
    Long capacity;

    public TrainRequestAdd() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
