package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addproductincart/{id}")
    public ResponseEntity addProductInCartController(@PathVariable("id") Long id, Principal principal){
        try {
            cartService.addProductInCartService(id, principal);
            return ResponseEntity.ok("Продукт добавлен");
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/showcart")
    public ResponseEntity showCartController(Principal principal){
        try{
            return ResponseEntity.ok(cartService.showCartService(principal));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/deleteproductincart/{id}")
    public ResponseEntity deleteProductController(Principal principal, @PathVariable Long id){
        try{
            cartService.deleteProductService(principal, id);
            return ResponseEntity.ok("Удалено");
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

}
