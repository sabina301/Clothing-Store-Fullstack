package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.entity.OrderEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.model.DTO.OrderDTO;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.OrderRepository;
import com.myclothingstore.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Transactional
    public String createOrderService(Principal principal, OrderDTO orderDTO){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("Пользователь не найден"));
        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElseThrow(()->new RuntimeException("Корзина не найдена"));
        Set<ProductEntity> products = new HashSet<>(cartEntity.getProducts());
        OrderEntity orderEntity = orderRepository.save(new OrderEntity((short) (100 + Math.random()*1000), orderDTO.getAddress(), "Заказ принят", userEntity, products));
        return "Заказ создан";
    }
    @Transactional
    public Set<OrderEntity> showAllUserOrdersService(Principal principal){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("Пользователь не найден"));
        return userEntity.getOrders();
    }

    @Transactional
    public Set<ProductEntity> showOneUserOrderService(Principal principal, Long id){
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("ПОльзователь не найден"));
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

    public Set<ProductEntity> showOneOrderService(Long id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Заказ не найден"));
        return orderEntity.getProducts();
    }

}
