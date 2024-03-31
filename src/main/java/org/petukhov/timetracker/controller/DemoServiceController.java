package org.petukhov.timetracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.petukhov.timetracker.service.impl.DemoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DemoServiceController {

    private final DemoServiceImpl demoService;

    @GetMapping("/demo-sync")
    @Operation(summary = "Синхронное выполнение метода")
    public String demoSync() {
        demoService.demoMethodSync();
        return "Demo method executed successfully.";
    }

    @GetMapping("/demo-async")
    @Operation(summary = "Асинхронное выполнение метода")
    public String demoAsync() {
        demoService.demoMethodAsync();
        return "Demo method executed successfully.";
    }
}

