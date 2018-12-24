package com.invillia.acme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "store")
@Data
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "genSeqStore", sequenceName = "seq_id_store", allocationSize = 1, initialValue = 1)
public class StoreEntity{

    public StoreEntity(){}
    
    @Id
    @GeneratedValue(generator = "genSeqStore", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Column(name="address")
    private String address;

}
