package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.ProductInOrderEntity;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.ProductInOrderRepository;
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

    @Autowired
    private ProductInOrderRepository productInOrderRepository;


    @Transactional
    public void addProductInCartService(Long productId, Long size, Principal principal) {
        String username = principal.getName();
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        CartEntity cartEntity = cartRepository.findById(userEntity.getCartEntity().getId()).get();

        ProductInOrderEntity productInOrderEntity = new ProductInOrderEntity();
        productInOrderEntity.setProductName(productEntity.getProductName());
        productInOrderEntity.setProductIcon(productEntity.getProductIcon());
        productInOrderEntity.setProductPrice(productEntity.getProductPrice());
        productInOrderEntity.setProductEntity(productEntity);
        productInOrderEntity.setSize(size);
        productInOrderRepository.save(productInOrderEntity);

        cartEntity.addProduct(productInOrderEntity);
        productInOrderEntity.setCartEntity(cartEntity);

        cartRepository.save(cartEntity);
        productRepository.save(productEntity);
    }

    @Transactional
    public Set<ProductInOrderEntity> showCartService(Principal principal){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).get();
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));
        Set<ProductInOrderEntity> products = cartEntity.getProducts();
        return products;
    }

    @Transactional
    public void deleteProductService(Principal principal, Long id){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).get();
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));
        ProductInOrderEntity productEntity = productInOrderRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Вещь не найдена"));

        if (cartEntity.getProducts().contains(productEntity)){
            cartEntity.deleteProduct(productEntity);
            cartRepository.save(cartEntity);
        } else {
            throw new EntityNotFoundException("Продукт не найден в корзине");
        }
    }
}
