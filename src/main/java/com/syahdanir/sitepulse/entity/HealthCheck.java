package com.syahdanir.sitepulse.entity;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HealthCheck extends PanacheEntity {
    public String status;

    public Integer statusCode;

    public Long responseTime;

    public LocalDateTime checkedAt;

    @ManyToOne
    @JsonIgnore
    public Website website;
}
