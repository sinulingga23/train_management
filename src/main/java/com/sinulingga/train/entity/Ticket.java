package com.sinulingga.train.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Ticket extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String wagon;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private Boolean isPurchased;
    @Column(nullable = false)
    private LocalDateTime purchaseAt;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Ticket() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWagon() {
        return wagon;
    }

    public void setWagon(String wagon) {
        this.wagon = wagon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }

    public LocalDateTime getPurchaseAt() {
        return purchaseAt;
    }

    public void setPurchaseAt(LocalDateTime purchaseAt) {
        this.purchaseAt = purchaseAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", wagon='" + wagon + '\'' +
                ", number='" + number + '\'' +
                ", isPurchased=" + isPurchased +
                ", purchaseAt=" + purchaseAt +
                ", price=" + price +
                ", schedule=" + schedule +
                '}';
    }
}
