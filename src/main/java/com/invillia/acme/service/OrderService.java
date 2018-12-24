package com.invillia.acme.service;

import com.invillia.acme.entity.OrderEntity;
import com.invillia.acme.entity.OrderItemEntity;
import com.invillia.acme.entity.StoreEntity;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.StoreRepository;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repository;
    
    public OrderEntity create(OrderEntity entity){

        entity.getOrderItemList().ifPresent(list -> list.stream().forEach(item -> item.setOrder(entity)));


        return repository.save(entity);
    }
    
    public Optional<OrderEntity> findById(Long id){
        return repository.findById(id);
    }
    
    public void canBeRefund(){
        
    }
    
    @Transactional
    public void refundOrder(Long orderId){
        
    }
    
}
