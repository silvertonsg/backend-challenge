package com.invillia.acme.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "genSeqOrder", sequenceName = "seq_id_order", allocationSize = 1, initialValue = 1)
public class OrderEntity {

    
    public OrderEntity(){}
    
    @Id
    @GeneratedValue(generator = "genSeqOrder", strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;
    
    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;
    
    @Column(name = "status", nullable = false)
    private String status ;

    @Column(name = "address")
    private String address;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItemList;
    
    public Optional<List<OrderItemEntity>> getOrderItemList(){
        return Optional.ofNullable(orderItemList);
    }

    
}
