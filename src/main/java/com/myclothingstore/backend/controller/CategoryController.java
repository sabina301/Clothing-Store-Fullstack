package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/addcategory")
    public ResponseEntity addCategoryController(@RequestBody CategoryEntity categoryEntity){
        try{
            return ResponseEntity.ok(categoryService.addCategoryService(categoryEntity));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/showallcategories")
    public ResponseEntity showAllCategoriesController() throws Exception{
        try{
            return ResponseEntity.ok(categoryService.showAllCategoriesService());
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/showproductsincategory/{id}")
    public ResponseEntity showProductsInCategoryController(@PathVariable("id") Integer categoryId){
        try{
            return ResponseEntity.ok(categoryService.showProductsInCategoryService(categoryId));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
