package com.syahdanir.sitepulse.service;

import com.syahdanir.sitepulse.entity.Website;
import com.syahdanir.sitepulse.repository.WebsiteRepository;
import com.syahdanir.sitepulse.dto.CreateWebsiteRequest;
import com.syahdanir.sitepulse.dto.UpdateWebsiteRequest;
import com.syahdanir.sitepulse.dto.WebsiteResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class WebsiteService {
    
    @Inject
    WebsiteRepository websiteRepository;

    public List<WebsiteResponse> getAllWebsites(){
        return websiteRepository.listAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Transactional
    public Website createWebsite(CreateWebsiteRequest request){
        
        Website website = new Website();

        website.name = request.name;
        website.url = request.url;
        website.active = true;

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
    public Website updateWebsite(Long id, UpdateWebsiteRequest request) {
        Website website = websiteRepository.findById(id);

        if(website == null) {
            throw new NotFoundException("Website Not Found!");
        }

        website.name = request.name;
        return website;
    }

    public WebsiteResponse toResponse(Website website){

        WebsiteResponse response = new WebsiteResponse();

        response.id = website.id;
        response.name = website.name;
        response.url = website.url;
        response.active = website.active;

        return response;
    }
}
