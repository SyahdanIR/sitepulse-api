package com.syahdanir.sitepulse.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

import com.syahdanir.sitepulse.dto.HealthCheckResponse;
import com.syahdanir.sitepulse.dto.WebsiteStatsResponse;
import com.syahdanir.sitepulse.entity.HealthCheck;
import com.syahdanir.sitepulse.entity.Website;
import com.syahdanir.sitepulse.repository.HealthCheckRepository;
import com.syahdanir.sitepulse.repository.WebsiteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class HealthCheckService {
    
    @Inject
    WebsiteRepository websiteRepository;

    @Inject
    HealthCheckRepository healthCheckRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Transactional
    public HealthCheck checkWebsite(Long websiteId) {
        Website website = websiteRepository.findById(websiteId);

        if(website == null) {
            throw new NotFoundException("Website not Found!");
        }

        long start = System.currentTimeMillis();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(website.url))
                .GET()
                .build();
            
            HttpResponse<Void> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.discarding()
            );

            long responseTime = System.currentTimeMillis() - start;

            HealthCheck healthCheck = new HealthCheck();

            healthCheck.website = website;
            healthCheck.statusCode = response.statusCode();
            healthCheck.responseTime = responseTime;
            healthCheck.status = 
                response.statusCode() >= 200 &&
                response.statusCode() < 400
                    ? "UP"
                    : "DOWN";
            healthCheck.checkedAt = LocalDateTime.now();

            healthCheckRepository.persist(healthCheck);

            return healthCheck;
        } catch (Exception e) {
            e.printStackTrace();

            long responseTime = System.currentTimeMillis() - start;
            HealthCheck healthCheck = new HealthCheck();

            healthCheck.website = website;
            healthCheck.status = "DOWN";
            healthCheck.responseTime = responseTime;
            healthCheck.checkedAt = LocalDateTime.now();

            healthCheckRepository.persist(healthCheck);

            return healthCheck;
        }
    }

    public List<HealthCheckResponse> getHealthChecks(Long websiteId) {
        Website website = websiteRepository.findById(websiteId);

        if (website == null) {
            throw new NotFoundException("Website Not Found!");
        }

        return healthCheckRepository.findByWebsiteId(websiteId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    public WebsiteStatsResponse getWebsiteStats(Long websiteId){
        Website website = websiteRepository.findById(websiteId);

        if(website == null) {
            throw new NotFoundException("Website Not Found!");
        }

        List<HealthCheck> checks = 
            healthCheckRepository.findByWebsiteId(websiteId);

        long totalChecks = checks.size();

        long successfulChecks = checks.stream()
            .filter(check -> "UP".equals(check.status))
            .count();

        long failedChecks = checks.stream()
            .filter(check -> "DOWN".equals(check.status))
            .count();

        double uptime = totalChecks == 0
            ? 0
            : ((double) successfulChecks / totalChecks) * 100;

        double averageResponseTime = checks.stream()
            .filter(check -> check.responseTime != null)
            .mapToLong(check -> check.responseTime)
            .average()
            .orElse(0);

        WebsiteStatsResponse stats = new WebsiteStatsResponse();

        stats.uptime = uptime;
        stats.averageResponseTime = averageResponseTime;
        stats.totalChecks = totalChecks;
        stats.successfulChecks = successfulChecks;
        stats.failedChecks = failedChecks;

        return stats;
    }

    public HealthCheckResponse toResponse(HealthCheck healthCheck) {

        HealthCheckResponse response = new HealthCheckResponse();
        
        response.id = healthCheck.id;
        response.status = healthCheck.status;
        response.statusCode = healthCheck.statusCode;
        response.responseTime = healthCheck.responseTime;
        response.checkedAt = healthCheck.checkedAt;

        return response;
    }
}
