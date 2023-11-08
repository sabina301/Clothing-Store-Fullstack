package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;
import com.myclothingstore.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/changeuserrole/{id}")
    public ResponseEntity changeUserRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(adminService.changeUserRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/changeadminrole/{id}")
    public ResponseEntity changeAdminRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(adminService.changeAdminRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }


    @PostMapping("/addcategory")
    public ResponseEntity addCategoryController(@RequestBody CategoryEntity categoryEntity){
        try{
            return ResponseEntity.ok(adminService.addCategoryService(categoryEntity));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/addproductincategory")
    public ResponseEntity addProductInCategoryController( @RequestBody ProductEntity productEntity){
        try{
            adminService.addProductInCategoryService(productEntity);
            return ResponseEntity.ok("Продукт добавлен");
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка1");
        }
    }

    @PutMapping("/{id}/changeproduct")
    public ResponseEntity changeProductController(@PathVariable Long id, @RequestBody ChangeProductDTO productDTO){
        try {
            return ResponseEntity.ok(adminService.changeProductService(id, productDTO));
        } catch (Exception err) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/{id}/deleteproduct")
    public ResponseEntity deleteProductController(@PathVariable Long id) throws Exception{
        try{
            adminService.deleteProductService(id);
            return ResponseEntity.ok("Удалено");
        } catch (Exception err) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
