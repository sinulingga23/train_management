package com.sinulingga.train.repository;

import com.sinulingga.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainRepository extends JpaRepository<Train, UUID> {
    Boolean existsByCode(String code);
}
