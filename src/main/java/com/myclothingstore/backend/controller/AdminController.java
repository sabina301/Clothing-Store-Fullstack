package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.entity.CategoryEntity;
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
    public ResponseEntity addcategory(@RequestBody CategoryEntity categoryEntity){
        try{
            return ResponseEntity.ok(adminService.addCategory(categoryEntity));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/addproductincategory")
    public ResponseEntity addproductincategory(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(adminService.changeAdminRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
