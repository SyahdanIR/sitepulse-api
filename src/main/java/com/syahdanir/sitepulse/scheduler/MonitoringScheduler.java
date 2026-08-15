package com.syahdanir.sitepulse.scheduler;

import java.util.List;

import com.syahdanir.sitepulse.entity.Website;
import com.syahdanir.sitepulse.repository.WebsiteRepository;
import com.syahdanir.sitepulse.service.HealthCheckService;

import io.quarkus.scheduler.Scheduled;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MonitoringScheduler {
    
    @Inject
    WebsiteRepository websiteRepository;

    @Inject
    HealthCheckService healthCheckService;

    @Scheduled(every = "5m")
    public void checkWebsites(){

        
        List<Website> websites =
            websiteRepository.list("active", true);

        for(Website website : websites) {
            healthCheckService.checkWebsite(website.id);
        }
    }
}
