package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.*;
import com.myclothingstore.backend.exception.CartNotFoundException;
import com.myclothingstore.backend.exception.UserNotFoundException;
import com.myclothingstore.backend.model.DTO.OrderDTO;
import com.myclothingstore.backend.repository.*;
import com.myclothingstore.backend.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;


    @Transactional
    public String createOrderService(Principal principal, OrderDTO orderDTO){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()->new UserNotFoundException("Пользователь не найден"));
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElseThrow(()->new CartNotFoundException("Корзина не найдена"));
        Set<ProductInOrderEntity> products = new HashSet<>(cartEntity.getProducts());
        for (ProductInOrderEntity productInOrder : products){
            ProductEntity product = productInOrder.getProductEntity();
            Set<ProductSizeEntity> productSizeEntities= product.getSizes();
            for (ProductSizeEntity productSizeEntity: productSizeEntities){
                if (productSizeEntity.getSize() == productInOrder.getSize()){
                    if (productSizeEntity.getQuantity()>=productInOrder.getQuantity()){
                        productSizeEntity.setQuantity(productSizeEntity.getQuantity() - productInOrder.getQuantity());
                    }
                    else{
                        return "Товар " + productInOrder.getProductName() + " закончился на складе :(";
                    }
                    break;
                }
            }
        }
        OrderEntity orderEntity = orderRepository.save(new OrderEntity((short) (100 + Math.random()*1000), orderDTO.getAddress(), "Заказ принят", userEntity, products));
        return "Заказ создан";
    }
    @Transactional
    public Set<OrderEntity> showAllUserOrdersService(Principal principal){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()->new UserNotFoundException("Пользователь не найден"));
        return userEntity.getOrders();
    }

    @Transactional
    public Set<ProductInOrderEntity> showOneUserOrderService(Principal principal, Long id){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()->new UserNotFoundException("Пользователь не найден"));
        Set<OrderEntity> orders = userEntity.getOrders();
        for (OrderEntity order: orders){
            if (order.getId() == id){
                return order.getProducts();
            }
        }
        return null;
    }

    public List<OrderEntity> showAllOrdersService(){
        return orderRepository.findAll();
    }

    public Set<ProductInOrderEntity> showOneOrderService(Long id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(()->new UserNotFoundException("Заказ не найден"));
        return orderEntity.getProducts();
    }

}
