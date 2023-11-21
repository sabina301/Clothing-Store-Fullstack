package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.ProductInOrderEntity;

import java.security.Principal;
import java.util.Set;

public interface CartService {
    void addProductInCartService(Long productId, Long size, Principal principal);
    Set<ProductInOrderEntity> showCartService(Principal principal);
    void deleteProductService(Principal principal, Long id);
}
