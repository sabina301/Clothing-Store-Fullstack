package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/addproduct/{id}/{size}")
    public ResponseEntity addProductInCartController(@PathVariable("id") Long id, @PathVariable("size") Long size,Principal principal){
        try {
            cartService.addProductInCartService(id, size, principal);
            return ResponseEntity.ok("Продукт добавлен");
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/show")
    public ResponseEntity showCartController(Principal principal){
        try{
            return ResponseEntity.ok(cartService.showCartService(principal));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity deleteProductController(Principal principal, @PathVariable Long id){
        try{
            cartService.deleteProductService(principal, id);
            return ResponseEntity.ok("Удалено");
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

}
