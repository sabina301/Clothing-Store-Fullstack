package com.myclothingstore.backend.repository;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategoryEntity(CategoryEntity categoryEntity);
}
