package com.myclothingstore.backend.controller;


import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;
import com.myclothingstore.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/addproductincategory")
    public ResponseEntity addProductInCategoryController(@RequestBody ProductEntity productEntity){
        try{
            productService.addProductInCategoryService(productEntity);
            return ResponseEntity.ok("Продукт добавлен");
        } catch (Exception err){
            return ResponseEntity.badRequest().body(err);
        }
    }

    @PutMapping("/admin/{id}/changeproduct")
    public ResponseEntity changeProductController(@PathVariable Long id, @RequestBody ChangeProductDTO productDTO){
        try {
            return ResponseEntity.ok(productService.changeProductService(id, productDTO));
        } catch (Exception err) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/admin/{id}/deleteproduct")
    public ResponseEntity deleteProductController(@PathVariable Long id) throws Exception{
        try{
            productService.deleteProductService(id);
            return ResponseEntity.ok("Удалено");
        } catch (Exception err) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
