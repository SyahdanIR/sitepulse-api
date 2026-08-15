package com.syahdanir.sitepulse.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/health")
public class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String health() {
        return """
                {
                    "status": "ok",
                    "message": "SitePulse API is running"
                }
                """;
    }
}
