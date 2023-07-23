package com.sinulingga.train.repository;

import com.sinulingga.train.entity.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainRepository extends JpaRepository<Train, UUID> {
    Boolean existsByCode(String code);
    Page<Train> findAll(Pageable pageable);
    @Query("select t from train t where t.name like %:name%")
    Page<Train> findByNameContaining(@Param("name") String name, Pageable pageable);
}
