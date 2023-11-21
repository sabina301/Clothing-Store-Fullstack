package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.entity.ProductSizeEntity;
import com.myclothingstore.backend.service.ProductSizeService;
import com.myclothingstore.backend.service.impl.ProductServiceImpl;
import com.myclothingstore.backend.service.impl.ProductSizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/productsize")
public class ProductSizeController {


    @Autowired
    public ProductSizeService productSizeService;

    @GetMapping("/{id}/show")
    public ResponseEntity showProductController(@PathVariable Long id) {
        return ResponseEntity.ok(productSizeService.showProductService(id));
    }

    @PostMapping("/add/{id}")
    public ResponseEntity addSizeController(@PathVariable Long id, @RequestBody ProductSizeEntity productSizeEntity){
        try{
            return ResponseEntity.ok(productSizeService.addSizeService(id, productSizeEntity));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
