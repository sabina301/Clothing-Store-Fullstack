package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.*;
import com.myclothingstore.backend.exception.ProductNotFoundException;
import com.myclothingstore.backend.exception.UserNotFoundException;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.ProductInOrderRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.CartService;
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
    public void addProductInCartService(Long productId, Principal principal) {
        String username = principal.getName();
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Продукт не найден"));

        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));

        Set<ProductInOrderEntity> products = cartEntity.getProducts();

        if (products.size()!=0) {
            for (ProductInOrderEntity product : products) {
                if (product.getProductEntity() == productEntity) {
                    product.setQuantity(product.getQuantity() + 1);
                    cartRepository.save(cartEntity);
                    productRepository.save(productEntity);
                    return;
                }
            }
        }
        ProductInOrderEntity productInOrderEntity = new ProductInOrderEntity();
        productInOrderEntity.setQuantity(1);
        productInOrderEntity.setProductName(productEntity.getProductName());
        productInOrderEntity.setProductIcon(productEntity.getProductIcon());
        productInOrderEntity.setProductPrice(productEntity.getProductPrice());
        productInOrderEntity.setProductEntity(productEntity);
        productInOrderRepository.save(productInOrderEntity);

        cartEntity.addProduct(productInOrderEntity);
        productInOrderEntity.setCartEntity(cartEntity);

        cartRepository.save(cartEntity);
        productRepository.save(productEntity);
    }

    @Transactional
    public Set<ProductInOrderEntity> showCartService(Principal principal){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()-> new UserNotFoundException("Пользователь не найден"));
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));
        return cartEntity.getProducts();
    }

    @Transactional
    public void deleteProductService(Principal principal, Long id){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).get();
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(new CartEntity(userEntity));
        ProductInOrderEntity productEntity = productInOrderRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Товар не найден"));

        if (cartEntity.getProducts().contains(productEntity)){
            cartEntity.deleteProduct(productEntity);
            cartRepository.save(cartEntity);
        } else {
            throw new ProductNotFoundException("Продукт не найден в корзине");
        }
    }
}
