package com.syahdanir.sitepulse.dto;

import java.time.LocalDateTime;

public class HealthCheckResponse {
    public Long id;
    public String status;
    public Integer statusCode;
    public Long responseTime;
    public LocalDateTime checkedAt;
}
