package com.syahdanir.sitepulse.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

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
            System.out.println("URL: " + website.url);
            System.out.println("STATUS CODE: " + response.statusCode());

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
}
