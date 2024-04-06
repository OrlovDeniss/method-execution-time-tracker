package ru.orlov.methodexecutiontimetracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orlov.methodexecutiontimetracker.model.TimeMetric;
import ru.orlov.methodexecutiontimetracker.repository.TimeMetricRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeTrackerService {

    private final TimeMetricRepository timeMetricRepository;

    @Transactional
    public void saveTimeMetric(TimeMetric timeMetric) {
        timeMetricRepository.save(timeMetric);
    }
}
