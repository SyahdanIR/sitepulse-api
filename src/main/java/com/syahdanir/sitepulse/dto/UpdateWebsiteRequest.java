package com.syahdanir.sitepulse.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateWebsiteRequest {
    
    @NotBlank
    public String name;

    @NotBlank
    public String url;

    public Boolean active;
}
