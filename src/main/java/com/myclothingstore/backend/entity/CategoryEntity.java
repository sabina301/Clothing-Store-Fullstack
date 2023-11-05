package com.myclothingstore.backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cartEntity")
    private Set<ProductEntity> products = new HashSet<>();
}
