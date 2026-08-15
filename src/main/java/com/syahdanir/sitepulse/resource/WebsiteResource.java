package com.syahdanir.sitepulse.resource;

import com.syahdanir.sitepulse.entity.HealthCheck;
import com.syahdanir.sitepulse.entity.Website;
import com.syahdanir.sitepulse.service.WebsiteService;
import com.syahdanir.sitepulse.service.HealthCheckService;
import com.syahdanir.sitepulse.dto.CreateWebsiteRequest;
import com.syahdanir.sitepulse.dto.HealthCheckResponse;
import com.syahdanir.sitepulse.dto.UpdateWebsiteRequest;
import com.syahdanir.sitepulse.dto.WebsiteResponse;
import com.syahdanir.sitepulse.dto.WebsiteStatsResponse;

import jakarta.validation.Valid;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
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

    @Inject
    HealthCheckService healthCheckService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WebsiteResponse> getAllWebsites() {
        return websiteService.getAllWebsites();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Website createWebsite(@Valid CreateWebsiteRequest request){
        return websiteService.createWebsite(request);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Website getWebsiteById(@PathParam("id") Long id) {
        return websiteService.getWebsiteById(id);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteWebsite(@PathParam("id") Long id) {
        websiteService.deleteWebsite(id);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Website updateWebsite(@PathParam("id") Long id, @Valid UpdateWebsiteRequest request) {
        return websiteService.updateWebsite(id, request);
    }

    @POST
    @Path("/{id}/check")
    @Produces(MediaType.APPLICATION_JSON)
    public HealthCheck checkWebsite(@PathParam("id") Long id) {
        return healthCheckService.checkWebsite(id);
    }

    @GET
    @Path("/{id}/health-checks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HealthCheckResponse> getHealthChecks(
        @PathParam("id") Long id
    ) {
        return healthCheckService.getHealthChecks(id);
    }

    @GET
    @Path("/{id}/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public WebsiteStatsResponse getWebsiteStats(
        @PathParam("id") Long id
    ) {
        return healthCheckService.getWebsiteStats(id);
    }
}
