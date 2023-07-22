package com.sinulingga.train.payload.response;

import com.sinulingga.train.entity.Train;
import jakarta.persistence.Id;

public class TrainResponseDetail {
    private String id;
    private String name;
    private String code;
    private String description;
    private Long capacity;

    public TrainResponseDetail() {

    }

    public TrainResponseDetail(Train train) {
        this.id = train.getId().toString();
        this.name = train.getName();
        this.code = train.getCode();
        this.description = train.getDescription();
        this.capacity = train.getCapacity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "TrainResponseDetail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
