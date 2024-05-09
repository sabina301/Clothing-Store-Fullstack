package com.myclothingstore.backend.controller;


import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;
import com.myclothingstore.backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/{id}/get")
    public ResponseEntity getProductController(@PathVariable Long id){
        try{
            return ResponseEntity.ok(productService.getProductService(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/addincategory/{id}")
    public ResponseEntity addProductInCategoryController(@RequestBody ProductEntity productEntity, @PathVariable Integer id){
        try{
            productService.addProductInCategoryService(productEntity, id);
            return ResponseEntity.ok("Продукт добавлен");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/change")
    public ResponseEntity changeProductController(@PathVariable Long id, @RequestBody ChangeProductDTO productDTO){
        try {
            return ResponseEntity.ok(productService.changeProductService(id, productDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteProductController(@PathVariable Long id) throws Exception{
        try{
            productService.deleteProductService(id);
            return ResponseEntity.ok("Товар удален");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
