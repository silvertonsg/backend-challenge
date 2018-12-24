package com.invillia.acme.resource;


import com.invillia.acme.entity.PaymentEntity;
import com.invillia.acme.resource.dto.ErrorDTO;
import com.invillia.acme.resource.dto.PaymentDTO;
import com.invillia.acme.resource.mapper.PaymentMapper;
import com.invillia.acme.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
@Api("/v1/payments")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentMapper paymentMapper;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Create payment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Accepted"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    public ResponseEntity createOrder(@RequestBody PaymentDTO payment){
        try {
            log.info(String.format("Call to create payment: %s", payment));

            PaymentEntity paymentEntity = paymentService.create(paymentMapper.mapTo(payment));

            return ResponseEntity.ok(paymentMapper.mapTo(paymentEntity));

        }catch(Exception e){
            e.printStackTrace();
            String msg = String.format("Error to create payment: %s, erro=%s",payment, e.getMessage());
            log.error(msg);
            return ResponseEntity.badRequest().body(ErrorDTO.builder().code("ERROR_ORDER").message(msg).build());
        }
    }
}
