package ru.orlov.methodexecutiontimetracker.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.orlov.methodexecutiontimetracker.model.TimeMetric;
import ru.orlov.methodexecutiontimetracker.model.TimeMetricStatistic;

import java.util.List;

@Repository
public interface TimeMetricRepository extends JpaRepository<TimeMetric, Long> {

    @Query("""
            SELECT tm.methodName           AS methodName,
                   tm.className            AS className,
                   tm.packageName          AS packageName,
                   AVG(tm.executionTimeMs) AS avgExecutionTime,
                   MAX(tm.executionTimeMs) AS maxExecutionTime
            FROM TimeMetric tm
            WHERE (:methodName IS NULL OR tm.methodName = :methodName)
              AND (:className IS NULL OR tm.className = :className)
              AND (:packageName IS NULL OR tm.packageName = :packageName)
            GROUP BY tm.methodName, tm.className, tm.packageName
            ORDER BY avgExecutionTime DESC
            """)
    List<TimeMetricStatistic> findAllTimeMetricStatistics(String methodName,
                                                          String className,
                                                          String packageName,
                                                          Pageable pageable);
}
