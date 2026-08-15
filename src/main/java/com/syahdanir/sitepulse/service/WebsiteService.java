package com.syahdanir.sitepulse.service;

import com.syahdanir.sitepulse.entity.Website;
import com.syahdanir.sitepulse.repository.WebsiteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class WebsiteService {
    
    @Inject
    WebsiteRepository websiteRepository;

    public List<Website> getAllWebsites(){
        return websiteRepository.listAll();
    }

    @Transactional
    public Website createWebsite(Website website){
        websiteRepository.persist(website);
        return website;
    }

    public Website getWebsiteById(Long id) {
        Website website = websiteRepository.findById(id);

        if(website == null) {
            throw new NotFoundException("Website Not Found!");
        }

        return website;
    }

    @Transactional
    public void deleteWebsite(Long id) {
        Website website = websiteRepository.findById(id);

        if(website == null) {
            throw new NotFoundException("Website Not Found!");
        }

        websiteRepository.deleteById(id);
    }

    @Transactional
    public Website updateWebsite(Long id, Website data) {
        Website website = websiteRepository.findById(id);

        if(website == null) {
            throw new NotFoundException("Website Not Found!");
        }

        website.name = data.name;
        return website;
    }
}
