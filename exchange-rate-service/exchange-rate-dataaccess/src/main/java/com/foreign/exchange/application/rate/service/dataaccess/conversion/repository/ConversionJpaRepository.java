package com.foreign.exchange.application.rate.service.dataaccess.conversion.repository;

import com.foreign.exchange.application.rate.service.dataaccess.conversion.entity.ConversionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversionJpaRepository extends JpaRepository<ConversionEntity, UUID> {

    Optional<ConversionEntity> findById(UUID id);

//    @Query("SELECT c FROM ConversionEntity c WHERE " +
//            "(:transactionId IS NULL OR c.id = :transactionId) " +
//            "AND (:transactionDate IS NULL OR c.createdAt = :transactionDate)")
//    Page<ConversionEntity> findByIdAndCreatedAt(UUID transactionId, ZonedDateTime transactionDate, Pageable pageable);

    @Query("SELECT c FROM ConversionEntity c WHERE " +
            "(:transactionId IS NULL OR c.id = :transactionId) " +
            "AND (:startDate IS NULL OR c.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR c.createdAt <= :endDate)")
    Page<ConversionEntity> findByIdAndCreatedAt(
            UUID transactionId,
            ZonedDateTime startDate, // Start of the date range
            ZonedDateTime endDate,   // End of the date range
            Pageable pageable
    );
}
