package com.syahdanir.sitepulse.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateWebsiteRequest {
    
    @NotBlank(message = "Name is Required")
    public String name;

    @NotBlank(message = "URL is Required")
    public String url;

    public Boolean active;
}
