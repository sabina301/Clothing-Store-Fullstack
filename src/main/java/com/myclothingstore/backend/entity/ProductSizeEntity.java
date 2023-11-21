package com.myclothingstore.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "product_size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSizeEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long size;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ProductEntity productEntity;
}
