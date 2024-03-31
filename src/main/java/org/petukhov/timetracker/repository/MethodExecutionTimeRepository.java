package org.petukhov.timetracker.repository;

import org.petukhov.timetracker.entity.MethodExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodExecutionTimeRepository extends JpaRepository<MethodExecutionTime, Long> {
}
