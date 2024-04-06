package ru.orlov.methodexecutiontimetracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.orlov.methodexecutiontimetracker.model.TimeMetricStatistic;
import ru.orlov.methodexecutiontimetracker.service.TimeMetricStatisticService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("time-metrics")
public class TimeMetricController {

    private static final String FROM = "1";
    private static final String SIZE = "10";

    private final TimeMetricStatisticService timeMetricStatisticService;

    @Operation(summary = "Получить статистику по времени выполнения методов")
    @ApiResponse(responseCode = "200", description = "Статистика по времени выполнения методов",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TimeMetricStatistic.class))})
    @GetMapping("statistics")
    public List<TimeMetricStatistic> getStatistics(@RequestParam(required = false) String methodName,
                                                   @RequestParam(required = false) String className,
                                                   @RequestParam(required = false) String packageName,
                                                   @RequestParam(defaultValue = FROM) @PositiveOrZero Integer from,
                                                   @RequestParam(defaultValue = SIZE) @Positive Integer size) {
        List<TimeMetricStatistic> statistics = timeMetricStatisticService.findAllTimeMetricStatistics(methodName, className, packageName, from, size);
        log.info("GET statistics, size: {}", statistics.size());
        return statistics;
    }
}
