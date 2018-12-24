package com.invillia.acme.resource.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StoreDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

}
