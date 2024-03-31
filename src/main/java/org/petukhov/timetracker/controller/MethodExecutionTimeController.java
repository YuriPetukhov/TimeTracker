package org.petukhov.timetracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.petukhov.timetracker.entity.MethodExecutionTime;
import org.petukhov.timetracker.service.MethodExecutionTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Контроллер для управления сущностью MethodExecutionTime.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/method-execution-time")
@Tag(name = "Method Execution Time")
public class MethodExecutionTimeController {

    private final MethodExecutionTimeService service;

    /**
     * Получение всех сущностей MethodExecutionTime.
     *
     * @param page Номер страницы (по умолчанию 1).
     * @param size Размер страницы (по умолчанию 10).
     * @return Список сущностей MethodExecutionTime.
     */
    @GetMapping
    @Operation(summary = "Получение всех сущностей MethodExecutionTime")
    public List<MethodExecutionTime> getAllMethodExecutionTimes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAll(page, size);
    }


    /**
     * Получение статистики по сущности MethodExecutionTime.
     *
     * @return Карта со статистическими данными.
     */
    @GetMapping("/stats")
    @Operation(summary = "Получение статистики по сущности MethodExecutionTime")
    public Map<String, Object> getMethodExecutionTimeStats() {
        return service.getStats();
    }
}
