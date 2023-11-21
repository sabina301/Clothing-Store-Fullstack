package com.myclothingstore.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Short code;
    private String address;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity userEntity;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "order_product",
        joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<ProductInOrderEntity> products = new HashSet<>();
    public OrderEntity(Short code, String address, String status, UserEntity userEntity, Set<ProductInOrderEntity> products){
        this.code=code;
        this.address=address;
        this.status=status;
        this.userEntity = userEntity;
        this.products=products;
    }
}
