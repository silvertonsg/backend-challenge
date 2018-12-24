package com.invillia.acme.resource.mapper;

import com.invillia.acme.entity.PaymentEntity;
import com.invillia.acme.resource.dto.PaymentDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    
    public List<PaymentEntity> mapToEntitylist(Optional<List<PaymentDTO>> itemListOp){
        if(itemListOp.isPresent()){
            List<PaymentDTO> itemList = itemListOp.get();
            return itemList.stream().map(item -> mapTo(item)).collect(Collectors.toList());
        }else{
            return null;
        }
        
    }
    
    public List<PaymentDTO> mapToDTOList(Optional<List<PaymentEntity>> itemListOp){
        if(itemListOp.isPresent()){
            List<PaymentEntity> itemList = itemListOp.get();
             return  itemList.stream().map(item -> mapTo(item)).collect(Collectors.toList());
        }else{
            return null;
        }
    }
    
    public PaymentEntity mapTo(PaymentDTO payment){
        return PaymentEntity.builder()
                .creditCardNumber(payment.getCreditCardNumber())
                .orderId(payment.getOrderId())
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus()).build();
    }
    
    public PaymentDTO mapTo(PaymentEntity payment){

        return PaymentDTO.builder()
                .id(payment.getId())
                .creditCardNumber(payment.getCreditCardNumber())
                .orderId(payment.getOrderId())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate()).build();
    }
}
