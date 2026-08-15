package com.syahdanir.sitepulse.repository;

import com.syahdanir.sitepulse.entity.HealthCheck;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthCheckRepository implements PanacheRepository<HealthCheck>{
}
