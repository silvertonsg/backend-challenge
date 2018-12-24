package com.invillia.acme.resource.mapper;

import com.invillia.acme.entity.StoreEntity;

import com.invillia.acme.resource.dto.StoreDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class StoreMapper {

;

    public StoreEntity mapTo(StoreDTO store){
        return StoreEntity.builder()
                .name(store.getName())
                .id(store.getId())
                .address(store.getAddress()).build();
    }

    public Optional<StoreDTO> mapTo(Optional<StoreEntity> entityOptional){
        if(entityOptional.isPresent()){
            StoreEntity entity = entityOptional.get();
            return Optional.of(StoreDTO.builder()
                    .id(entity.getId())
                    .address(entity.getAddress())
                    .name(entity.getName()).build());
        }
        return Optional.ofNullable(null);

    }

   
}
