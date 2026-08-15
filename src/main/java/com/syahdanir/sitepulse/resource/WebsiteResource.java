package com.syahdanir.sitepulse.resource;

import com.syahdanir.sitepulse.entity.Website;
import com.syahdanir.sitepulse.service.WebsiteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/api/websites")
public class WebsiteResource {
    
    @Inject
    WebsiteService websiteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Website> getAllWebsites() {
        return websiteService.getAllWebsites();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Website createWebsite(Website website){
        return websiteService.createWebsite(website);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Website getWebsiteById(@PathParam("id") Long id) {
        return websiteService.getWebsiteById(id);
    } 
}
