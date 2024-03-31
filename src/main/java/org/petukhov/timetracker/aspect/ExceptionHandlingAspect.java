package org.petukhov.timetracker.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Aspect
@Order(0)
@Slf4j
public class ExceptionHandlingAspect {

    @Pointcut("execution(* org.petukhov.timetracker.service..*(..))")
    public void trackTime() {

    }

    @AfterThrowing(pointcut = "trackTime()", throwing = "throwable")
    public void logError(JoinPoint joinPoint, Throwable throwable) {
        log.error("An error occurred while executing the method: " + joinPoint.getSignature().getName(), throwable);
    }
}
