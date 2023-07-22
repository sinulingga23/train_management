package com.sinulingga.train.entity;

import com.sinulingga.train.payload.request.TrainRequestAdd;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "train")
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = "code"
        )
)
public class Train extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "varchar(36)")
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = true)
    @Nullable
    private String description;
    @Column(nullable = false, length = 16)
    private String code;
    @Column(nullable = false)
    private Long capacity;

    public Train() {

    }

    public Train(TrainRequestAdd request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.capacity = request.getCapacity();
        this.code = request.getCode();
        this.id = UUID.randomUUID();
        this.setCreatedAt(LocalDateTime.now());
        this.setCreatedBy("system");
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
