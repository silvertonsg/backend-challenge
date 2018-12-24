package com.invillia.acme.resource;

import com.invillia.acme.entity.OrderEntity;
import com.invillia.acme.resource.dto.ErrorDTO;
import com.invillia.acme.resource.dto.OrderDTO;
import com.invillia.acme.resource.dto.StoreDTO;
import com.invillia.acme.resource.mapper.OrderMapper;
import com.invillia.acme.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@Slf4j
@Api("/v1/orders")
public class OrderResource {
    
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderService orderService;
    
     @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Accepted"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    public ResponseEntity createOrder(@RequestBody OrderDTO order){
        try {
            log.info(String.format("Call to create order: %s", order));
            
            OrderEntity  orderEntity = orderService.create(orderMapper.mapTo(order));
           
            return ResponseEntity.ok(orderMapper.mapTo(Optional.ofNullable(orderEntity)));
           
        }catch(Exception e){
            e.printStackTrace();
            String msg = String.format("Error call to create order: %s, erro=%s",order, e.getMessage());
            log.error(msg);
            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_ORDER").message(msg).build());
        }
    }  
    
    
     @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Find order by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public HttpEntity findOrderbyId(@PathVariable("id") Long id){
        try {
            log.info(String.format("Call to find order by id %d",id));

            Optional<OrderDTO> orderDTOOp = orderMapper.mapTo(orderService.findById(id));
            if(orderDTOOp.isPresent()){
                return ResponseEntity.ok(orderDTOOp.get());
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch(Exception e){
            String msg = String.format("Error to find order with id: %d, erro=%s",id, e.getMessage());
            log.error(msg);
            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_ORDER").message(msg).build());
        }
    }

//    @RequestMapping(value = "/{orderId}/refound",
//            method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
//            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
//    )
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @ApiOperation(value = "Update order status")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Ok"),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 422, message = "Unprocessable Entity"),
//            @ApiResponse(code = 400, message = "Bad Request"),
//            @ApiResponse(code = 404, message = "Not found")
//    })
//    public HttpEntity refundOrder(@PathVariable("orderId") Long orderId){
//        try {
//            log.info(String.format("Call to refund order with id %d",orderId));
//
//            Optional<OrderDTO> orderDTOOp = orderMapper.mapTo(orderService.findById(id));
//            if(orderDTOOp.isPresent()){
//                return ResponseEntity.ok(orderDTOOp.get());
//            }else{
//                return ResponseEntity.notFound().build();
//            }
//
//        }catch(Exception e){
//            String msg = String.format("Error refund order with id: %d, erro=%s",orderId, e.getMessage());
//            log.error(msg);
//            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_ORDER").message(msg).build());
//        }
//    }

}
