CREATE TABLE time_metric
(
    id                BIGSERIAL PRIMARY KEY,
    execution_time_ms BIGINT       NOT NULL,
    class_name        VARCHAR(255) NOT NULL,
    method_name       VARCHAR(255) NOT NULL,
    package_name      VARCHAR(255) NOT NULL
);
