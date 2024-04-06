package ru.orlov.methodexecutiontimetracker.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.orlov.methodexecutiontimetracker.exception.TrackTimeException;
import ru.orlov.methodexecutiontimetracker.model.TimeMetric;
import ru.orlov.methodexecutiontimetracker.service.TimeTrackerService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TimeMetricAspect {

    private final TimeTrackerService timeTrackerService;

    @Around("@annotation(ru.orlov.methodexecutiontimetracker.annotation.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) {
        return processTrackTime(joinPoint);
    }

    @Around("@annotation(ru.orlov.methodexecutiontimetracker.annotation.TrackTimeAsync)")
    public Object trackTimeAsync(ProceedingJoinPoint joinPoint) {
        return processTrackTimeAsync(joinPoint);
    }

    private Object processTrackTimeAsync(ProceedingJoinPoint joinPoint) {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> processTrackTime(joinPoint));
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Не удалось выполнить асинхронно: {}", joinPoint.getSignature().toString(), e);
            throw new TrackTimeException(e);
        }
    }

    private Object processTrackTime(ProceedingJoinPoint joinPoint) {
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            long executionTimeMs = System.currentTimeMillis() - startTime;
            TimeMetric timeMetric = buildTimeMetric(joinPoint, executionTimeMs);
            timeTrackerService.saveTimeMetric(timeMetric);
            return proceed;
        } catch (Throwable e) {
            log.error("Исключение в целевом методе: {}", joinPoint.getSignature().toString(), e);
            throw new TrackTimeException(e);
        }
    }

    private TimeMetric buildTimeMetric(ProceedingJoinPoint joinPoint, long executionTimeMs) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String packageName = joinPoint.getTarget().getClass().getPackage().getName();
        return TimeMetric.builder()
                .executionTimeMs(executionTimeMs)
                .methodName(methodName)
                .className(className)
                .packageName(packageName)
                .build();
    }
}
