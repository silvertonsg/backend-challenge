package com.invillia.acme.resource.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String code;
    private String message;
    
}
