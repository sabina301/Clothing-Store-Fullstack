package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/add")
    public ResponseEntity addCategoryController(@RequestBody CategoryEntity categoryEntity){
        try{
            return ResponseEntity.ok(categoryService.addCategoryService(categoryEntity));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/showall")
    public ResponseEntity showAllCategoriesController(){
        return ResponseEntity.ok(categoryService.showAllCategoriesService());
    }

    @GetMapping("/showproducts/{id}")
    public ResponseEntity showProductsInCategoryController(@PathVariable("id") Integer categoryId){
        try{
            return ResponseEntity.ok(categoryService.showProductsInCategoryService(categoryId));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
