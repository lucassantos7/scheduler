package com.maverick.scheduler.repository;

import com.maverick.scheduler.model.WorkConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkConfigRepository extends JpaRepository<WorkConfig, Long> {
}