package com.invillia.acme.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderItemDTO {
    
    private Long id;
    @NotNull
    private String description;
    @NotNull
    private Integer unitPrice;
    @NotNull
    private Integer quantity;
    
    private String status;
    
}
