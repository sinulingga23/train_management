package com.sinulingga.train.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
    @ManyToOne
    @JoinColumn(name = "departure_id",nullable = false)
    private Station departure;
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Station destination;
    @Column(nullable = false)
    private LocalDateTime departureAt;
    @Column(nullable = false)
    private LocalDateTime arrivedAt;

    public Schedule() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getDeparture() {
        return departure;
    }

    public void setDeparture(Station departure) {
        this.departure = departure;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(LocalDateTime departureAt) {
        this.departureAt = departureAt;
    }

    public LocalDateTime getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(LocalDateTime arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", train=" + train +
                ", departure=" + departure +
                ", destination=" + destination +
                ", departureAt=" + departureAt +
                ", arrivedAt=" + arrivedAt +
                '}';
    }
}
