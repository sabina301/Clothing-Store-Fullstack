package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.OrderEntity;
import com.myclothingstore.backend.entity.ProductInOrderEntity;
import com.myclothingstore.backend.model.DTO.OrderDTO;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface OrderService {
    String createOrderService(Principal principal);
    Set<OrderEntity> showAllUserOrdersService(Principal principal);
    Set<ProductInOrderEntity> showOneUserOrderService(Principal principal, Long id);
    List<OrderEntity> showAllOrdersService();
    Set<ProductInOrderEntity> showOneOrderService(Long id);
}
