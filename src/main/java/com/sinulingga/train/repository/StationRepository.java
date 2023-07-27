package com.sinulingga.train.repository;

import com.sinulingga.train.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<Station, UUID> {
    Boolean existsByCode(String code);
}
