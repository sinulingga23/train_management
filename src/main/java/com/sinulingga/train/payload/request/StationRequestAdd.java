package com.sinulingga.train.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationRequestAdd {

    @JsonProperty(value = "name", required = true)
    private String name;
    @JsonProperty(value = "description", required = true)
    private String description;
    @JsonProperty(value = "address", required = true)
    private String address;
    @JsonProperty(value = "code")
    private String code;

    public StationRequestAdd() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StationRequestAdd{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
