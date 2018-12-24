package com.invillia.acme.service;


import com.invillia.acme.entity.PaymentEntity;
import com.invillia.acme.repository.PaymentRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public PaymentEntity create(PaymentEntity entity){
       
        return repository.save(entity);
    }
}
