package com.myclothingstore.backend.controller;


import com.myclothingstore.backend.service.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UniversalController {

    @Autowired
    private UniversalService universalService;

    @GetMapping("/showallcategories")
    public ResponseEntity showAllCategoriesController() throws Exception{
        try{
            return ResponseEntity.ok(universalService.showAllCategoriesService());
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/showproductsincategory/{id}")
    public ResponseEntity showProductsInCategoryController(@PathVariable("id") Integer categoryId){
        try{
            return ResponseEntity.ok(universalService.showProductsInCategoryService(categoryId));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

}
