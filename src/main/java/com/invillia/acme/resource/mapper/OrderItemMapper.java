package com.invillia.acme.resource.mapper;

import com.invillia.acme.entity.OrderItemEntity;
import com.invillia.acme.resource.dto.OrderItemDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    
    public List<OrderItemEntity> mapToEntitylist(List<OrderItemDTO> itemList){
        return itemList.stream().map(item -> mapTo(item)).collect(Collectors.toList());
    }
    
    public List<OrderItemDTO> mapToDTOList(Optional<List<OrderItemEntity>> itemListOp){
        
        if(itemListOp.isPresent()){
            List<OrderItemEntity> itemList = itemListOp.get();
             return itemList.stream().map(item -> mapTo(item)).collect(Collectors.toList());
        }else{
            return null;
        }
       
    }
    
    private OrderItemEntity mapTo(OrderItemDTO item){
        return OrderItemEntity.builder()
                .description(item.getDescription())
                .id(item.getId())
                .status(item.getStatus())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice()).build();
    }
    
    private OrderItemDTO mapTo(OrderItemEntity entity){
        return OrderItemDTO.builder()
                .description(entity.getDescription())
                .id(entity.getId())
                .status(entity.getStatus())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice()).build();
    }
}
