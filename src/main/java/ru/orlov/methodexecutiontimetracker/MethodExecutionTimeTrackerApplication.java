package ru.orlov.methodexecutiontimetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MethodExecutionTimeTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MethodExecutionTimeTrackerApplication.class, args);
    }

}
