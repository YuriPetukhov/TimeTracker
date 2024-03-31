package org.petukhov.timetracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.petukhov.timetracker.entity.MethodExecutionTime;
import org.petukhov.timetracker.repository.MethodExecutionTimeRepository;
import org.petukhov.timetracker.service.MethodExecutionTimeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MethodExecutionTimeServiceImpl implements MethodExecutionTimeService {

    private final MethodExecutionTimeRepository methodExecutionTimeRepository;

    @Async
    public void save(MethodExecutionTime executionTime) {
        methodExecutionTimeRepository.save(executionTime);
    }

    @Override
    public List<MethodExecutionTime> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return methodExecutionTimeRepository.findAll(pageRequest).getContent();
    }
    @Override
    public Map<String, Object> getStats() {
        List<MethodExecutionTime> allExecutionTimes = methodExecutionTimeRepository.findAll();

        return allExecutionTimes.stream()
                .collect(Collectors.groupingBy(MethodExecutionTime::getMethodName,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            long totalCount = list.size();
                            double totalTime = list.stream()
                                    .mapToLong(MethodExecutionTime::getExecutionTime)
                                    .sum();
                            double averageTime = totalTime / totalCount;

                            return Map.of("totalCount", totalCount, "totalTime", totalTime, "averageTime", String.format("%.2f", averageTime));
                        })));
    }

}

