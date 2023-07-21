package com.sinulingga.train.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse {
    @JsonProperty(namespace = "responseCode")
    String responseCode;
    @JsonProperty(namespace = "responseDesc")
    String responseDesc;
    @JsonProperty(namespace = "data")
    Object data;

    public GenericResponse() {}

    public GenericResponse(String responseCode, String responseDesc, Object data) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.data = data;
    }

    public GenericResponse(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                ", data=" + data +
                '}';
    }
}
