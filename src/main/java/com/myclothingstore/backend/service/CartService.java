package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void addProductInCartService(Long productId, Principal principal) {
        String username = principal.getName();
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        CartEntity cartEntity = cartRepository.findById(userEntity.getCartEntity().getId()).get();

        cartEntity.addProduct(productEntity);
        productEntity.setCartEntity(cartEntity);

        cartRepository.save(cartEntity);
        productRepository.save(productEntity);
    }

    @Transactional
    public Set<ProductEntity> showCartService(Principal principal){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).get();
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));
        Set<ProductEntity> products = cartEntity.getProducts();
        System.out.println(products);
        return products;
    }
}
