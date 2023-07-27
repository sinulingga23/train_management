package com.sinulingga.train.entity;

import com.sinulingga.train.payload.request.StationRequestAdd;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "station")
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = "code"
        )
)
public class Station extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 16)
    private String code;

    public Station() {

    }

    public Station(StationRequestAdd request) {
        this.id = UUID.randomUUID();
        this.address = request.getAddress();
        this.code = request.getCode();
        this.description = request.getDescription();
        this.name = request.getName();
        this.setCreatedBy("System");
        this.setCreatedAt(LocalDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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


}
