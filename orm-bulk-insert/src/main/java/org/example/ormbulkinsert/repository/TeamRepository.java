package org.example.ormbulkinsert.repository;

import org.example.ormbulkinsert.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
