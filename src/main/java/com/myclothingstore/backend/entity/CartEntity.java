package com.myclothingstore.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myclothingstore.backend.model.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@Getter
@Setter
public class CartEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cartEntity", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ProductInOrderEntity> products = new HashSet<>();
    public CartEntity(UserEntity user) {
        this.userEntity = user;
    }
    public void addProduct(ProductInOrderEntity ProductInOrderEntity){
        products.add(ProductInOrderEntity);
        ProductInOrderEntity.setCartEntity(this);
    }
    public void deleteProduct(ProductInOrderEntity productEntity){

        if (productEntity.getQuantity()<=1){
            products.remove(productEntity);
            productEntity.setCartEntity(null);
        } else {
            productEntity.setQuantity(productEntity.getQuantity() - 1);
        }
    }
}