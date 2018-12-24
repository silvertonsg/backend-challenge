package com.invillia.acme.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name="payment")
@Data
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "genSeqPayment", sequenceName = "seq_id_payment", allocationSize = 1, initialValue = 1)
public class PaymentEntity {
    
    public PaymentEntity(){}
    
    @Id
    @GeneratedValue(generator = "genSeqOrderItem", strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;
    
    
    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber ;
    
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
    
    @Column(name = "status", nullable = false)
    private String status ;
    
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", columnDefinition = "order_id",insertable = false, updatable = false)
    private OrderEntity order;
    
    public Optional<OrderEntity> getOrder(){
        return Optional.ofNullable(order);
    }
}
