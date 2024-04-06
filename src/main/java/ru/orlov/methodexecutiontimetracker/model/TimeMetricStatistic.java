package ru.orlov.methodexecutiontimetracker.model;

public interface TimeMetricStatistic {

    String getMethodName();

    String getClassName();

    String getPackageName();

    long getAvgExecutionTime();

    long getMaxExecutionTime();
}
