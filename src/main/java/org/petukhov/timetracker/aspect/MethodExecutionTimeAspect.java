package org.petukhov.timetracker.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.petukhov.timetracker.entity.MethodExecutionTime;
import org.petukhov.timetracker.service.impl.MethodExecutionTimeServiceImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Aspect
@Order(1)
@Slf4j
public class MethodExecutionTimeAspect {

    private final MethodExecutionTimeServiceImpl methodExecutionTimeService;

    @Pointcut("@annotation(org.petukhov.timetracker.annotation.TrackTime)")
    public void trackTime() {

    }

    @Pointcut("@annotation(org.petukhov.timetracker.annotation.TrackAsyncTime)")
    public void trackAsyncTime() {}

    @Around("trackTime()")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logTime(joinPoint, endTime - startTime);

        saveResult(joinPoint, executionTime);

        return result;
    }

    @Around("trackAsyncTime()")
    public CompletableFuture<Object> trackAsyncTime(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();

        return CompletableFuture.supplyAsync(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                log.error("An error occurred while executing the method: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }).whenComplete((result, throwable) -> {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            logTime(joinPoint, endTime - startTime);

            saveResult(joinPoint, executionTime);
        });
    }

    private void logTime(ProceedingJoinPoint joinPoint, long executionTime) {
        log.info("Method: {}, Execution time: {} ms", joinPoint.getSignature().getName(), executionTime);
    }

    private void saveResult(ProceedingJoinPoint joinPoint, long executionTime) {
        MethodExecutionTime methodExecutionTime = new MethodExecutionTime();
        methodExecutionTime.setMethodName(joinPoint.getSignature().getName());
        methodExecutionTime.setExecutionTime(executionTime);
        methodExecutionTimeService.save(methodExecutionTime);
    }
}

