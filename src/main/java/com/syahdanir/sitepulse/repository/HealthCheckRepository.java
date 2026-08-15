package com.syahdanir.sitepulse.repository;

import java.util.List;

import com.syahdanir.sitepulse.entity.HealthCheck;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthCheckRepository implements PanacheRepository<HealthCheck>{
    public List<HealthCheck> findByWebsiteId(Long websiteId){
        return find("website.id", websiteId).list();
    }
}
