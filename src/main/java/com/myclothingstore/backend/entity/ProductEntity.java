package com.myclothingstore.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String productDescription;
    private String productIcon;
    private Integer productPrice;
    private String productStatus;
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CategoryEntity categoryEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name="order_product",
        joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private Set<OrderEntity> orders = new HashSet<>();

}
