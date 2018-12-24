package com.invillia.acme.resource.mapper;

import com.invillia.acme.entity.OrderEntity;
import com.invillia.acme.resource.dto.OrderDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    

    
    @Autowired
    private OrderItemMapper orderItemMapper;
    
    @Autowired
    private PaymentMapper paymentMapper;
    
    @Autowired
    private StoreMapper storeMapper;
    
    public OrderEntity mapTo(OrderDTO order){
        
        return OrderEntity.builder()
                .id(order.getId())
                .status(order.getStatus())
                .address(order.getAddress())
                .confirmationDate(order.getConfirmationDate())
                .orderItemList(orderItemMapper.mapToEntitylist(order.getItems())).build();
                
    }
    
     public Optional<OrderDTO> mapTo(Optional<OrderEntity> orderOp){
        
         if(orderOp.isPresent()){
             OrderEntity order = orderOp.get();
              return Optional.of(OrderDTO.builder()
                .id(order.getId())
                .confirmationDate(order.getConfirmationDate())
                .status(order.getStatus())
                .address(order.getAddress())
                .items(orderItemMapper.mapToDTOList(order.getOrderItemList())).build());
         }else{
             return Optional.ofNullable(null);
         }
       
                
    }
    
}
