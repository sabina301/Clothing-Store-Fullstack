package com.myclothingstore.backend.repository;

import com.myclothingstore.backend.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {

}
