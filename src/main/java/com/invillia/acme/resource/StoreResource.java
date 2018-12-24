package com.invillia.acme.resource;

import com.invillia.acme.entity.StoreEntity;
import com.invillia.acme.resource.dto.ErrorDTO;
import com.invillia.acme.resource.dto.StoreDTO;
import com.invillia.acme.resource.mapper.StoreMapper;
import com.invillia.acme.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/stores")
@Slf4j
@Api("/v1/stores")
public class StoreResource {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create store")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    public HttpEntity createStore(@RequestBody @Valid StoreDTO store){
        try {
            log.info(String.format("Call to save store: %s",store));
            StoreEntity entity = storeService.createStore(storeMapper.mapTo(store));
            return ResponseEntity.ok(storeMapper.mapTo(Optional.ofNullable(entity)));
        }catch(Exception e){
            String msg = String.format("Error call to save store: %s, error: %s",e.getMessage());
            log.error(msg);
       
            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_STORE").message(msg).build());
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Updates store")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public HttpEntity updateStore(@PathVariable("id") Long id, @RequestBody StoreDTO store){
        try {
            log.info(String.format("Call to update store with id %d",id));
            store.setId(id);
            StoreEntity entity =  storeService.updateStore(storeMapper.mapTo(store));
            return ResponseEntity.ok(storeMapper.mapTo(Optional.ofNullable(entity)));
        }catch(EntityNotFoundException e){
             return ResponseEntity.notFound().build();
        } catch(Exception e){
            String msg = String.format("Call to update store with id: %d, erro=%s",id, e.getMessage());
            log.error(msg);
            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_STORE").message(msg).build());
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Find store by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public HttpEntity findStore(@PathVariable("id") Long id){
        try {
            log.info(String.format("Find store with id %d",id));

            Optional<StoreDTO> storeDTO = storeMapper.mapTo(storeService.findById(id));
            if(storeDTO.isPresent()){
                return ResponseEntity.ok(storeDTO.get());
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch(Exception e){
            String msg = String.format("Error to find store with id: %d, erro=%s",id, e.getMessage());
            log.error(msg);
            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_STORE").message(msg).build());
        }
    }

}
