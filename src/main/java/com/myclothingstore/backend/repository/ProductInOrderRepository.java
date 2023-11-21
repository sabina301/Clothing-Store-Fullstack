package com.myclothingstore.backend.repository;

import com.myclothingstore.backend.entity.ProductInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrderEntity, Long> {
}
