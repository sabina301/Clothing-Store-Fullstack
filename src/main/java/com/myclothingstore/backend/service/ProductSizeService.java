package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.ProductSizeEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

public interface ProductSizeService {
    String addSizeService(@PathVariable Long productId, ProductSizeEntity productSizeEntity);
    Set<ProductSizeEntity> showProductService(Long id);

}
