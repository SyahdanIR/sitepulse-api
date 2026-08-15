package com.syahdanir.sitepulse.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateWebsiteRequest {
    
    @NotBlank
    public String name;
    
    @NotBlank
    public String url;
}
