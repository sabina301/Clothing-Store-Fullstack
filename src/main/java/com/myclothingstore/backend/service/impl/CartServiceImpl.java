package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

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
        return products;
    }

    @Transactional
    public void deleteProductService(Principal principal, Long id){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).get();
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Вещь не найдена"));

        if (cartEntity.getProducts().contains(productEntity)){
            cartEntity.deleteProduct(productEntity);
            cartRepository.save(cartEntity);
        } else {
            throw new EntityNotFoundException("Продукт не найден в корзине");
        }
    }
}
