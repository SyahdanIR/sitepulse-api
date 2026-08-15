package com.syahdanir.sitepulse.repository;

import com.syahdanir.sitepulse.entity.Website;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WebsiteRepository implements PanacheRepository<Website>{
}