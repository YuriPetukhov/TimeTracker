package org.petukhov.timetracker.service;

import org.petukhov.timetracker.entity.MethodExecutionTime;

import java.util.List;
import java.util.Map;

public interface MethodExecutionTimeService {
    List<MethodExecutionTime> getAll(int page, int size);

    Map<String, Object> getStats();

}
