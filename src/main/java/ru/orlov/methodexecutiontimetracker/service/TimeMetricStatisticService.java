package ru.orlov.methodexecutiontimetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.orlov.methodexecutiontimetracker.model.TimeMetricStatistic;
import ru.orlov.methodexecutiontimetracker.repository.TimeMetricRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeMetricStatisticService {

    private final TimeMetricRepository timeMetricRepository;

    public List<TimeMetricStatistic> findAllTimeMetricStatistics(String methodName,
                                                                 String className,
                                                                 String packageName,
                                                                 Integer from,
                                                                 Integer size) {
        Pageable pageRequest = PageRequest.of(from / size, size);
        return timeMetricRepository.findAllTimeMetricStatistics(methodName, className, packageName, pageRequest);
    }
}
