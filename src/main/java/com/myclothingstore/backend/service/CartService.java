package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.ProductEntity;

import java.security.Principal;
import java.util.Set;

public interface CartService {
    void addProductInCartService(Long productId, Principal principal);
    Set<ProductEntity> showCartService(Principal principal);
    void deleteProductService(Principal principal, Long id);
}
