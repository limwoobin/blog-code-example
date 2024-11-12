package org.example.ormbulkinsert.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<TempEntity, Long> {
}
