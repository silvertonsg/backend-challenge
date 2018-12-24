package com.invillia.acme.service;

import com.invillia.acme.entity.StoreEntity;
import com.invillia.acme.repository.StoreRepository;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

@Service
public class StoreService {

    @Autowired
    private StoreRepository repository;

    public StoreEntity createStore(StoreEntity store){
 
        return repository.save(store);
    }

    public StoreEntity updateStore(StoreEntity store){
       
         Optional<StoreEntity> storeOptional = repository.findById(store.getId());
         if(storeOptional.isPresent()){
     
            return repository.save(store);
         }else{
             throw  new EntityNotFoundException();
         }
         
    }

    public Optional<StoreEntity> findById(Long id){
        return repository.findById(id);
    }
}
