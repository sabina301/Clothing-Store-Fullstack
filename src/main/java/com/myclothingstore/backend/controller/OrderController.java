package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.model.DTO.OrderDTO;
import com.myclothingstore.backend.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResponseEntity createOrderController(Principal principal, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrderService(principal, orderDTO));
    }

    @GetMapping("/user/showall")
    public ResponseEntity showAllUserOrdersController(Principal principal){
        try{
            return ResponseEntity.ok(orderService.showAllUserOrdersService(principal));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/user/showone/{id}")
    public ResponseEntity showOneUserOrderController(Principal principal, @PathVariable Long id){
        try{
            return ResponseEntity.ok(orderService.showOneUserOrderService(principal,id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/admin/showall")
    public ResponseEntity showAllOrdersController(){
        try {
            return ResponseEntity.ok(orderService.showAllOrdersService());
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/admin/showone/{id}")
    public ResponseEntity showOneOrderController(@PathVariable Long id){
        try{
            return ResponseEntity.ok(orderService.showOneOrderService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

}
