package com.myclothingstore.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;
    private String  name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryEntity", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ProductEntity> products = new HashSet<>();
    public void addProduct(ProductEntity product) {
        products.add(product);
        product.setCategoryEntity(this);
    }
    public void removeProduct(ProductEntity product) {
        products.remove(product);
        product.setCategoryEntity(null);
    }
}
