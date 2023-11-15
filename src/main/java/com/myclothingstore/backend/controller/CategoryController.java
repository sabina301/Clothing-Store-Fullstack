package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity addCategoryController(@RequestBody CategoryEntity categoryEntity){
        try{
            return ResponseEntity.ok(categoryService.addCategoryService(categoryEntity));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/showall")
    public ResponseEntity showAllCategoriesController() throws Exception{
        try{
            return ResponseEntity.ok(categoryService.showAllCategoriesService());
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/showproducts/{id}")
    public ResponseEntity showProductsInCategoryController(@PathVariable("id") Integer categoryId){
        try{
            return ResponseEntity.ok(categoryService.showProductsInCategoryService(categoryId));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
