package com.example.lockexample.redisson.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsByCode(String code);

    Long countByCode(String code);
}
