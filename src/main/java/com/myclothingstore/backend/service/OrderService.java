package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.OrderEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.model.DTO.OrderDTO;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface OrderService {
    String createOrderService(Principal principal, OrderDTO orderDTO);
    Set<OrderEntity> showAllUserOrdersService(Principal principal);
    Set<ProductEntity> showOneUserOrderService(Principal principal, Long id);
    List<OrderEntity> showAllOrdersService();
    Set<ProductEntity> showOneOrderService(Long id);
}
