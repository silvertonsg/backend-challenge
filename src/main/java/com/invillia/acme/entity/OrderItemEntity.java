package com.invillia.acme.entity;

import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="order_item")
@Data
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "genSeqOrderItem", sequenceName = "seq_id_order_item", allocationSize = 1, initialValue = 1)
public class OrderItemEntity {
    public OrderItemEntity(){}
    
    @Id
    @GeneratedValue(generator = "genSeqOrderItem", strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;
    
    @Column(name = "description", nullable = false)
    private String description;
     
    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
     @Column(name = "status", nullable = false)
    private String status ;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", columnDefinition = "order_id")
    private OrderEntity order;
    
    public Optional<OrderEntity> getOrder(){
        return Optional.ofNullable(order);
    }
}
