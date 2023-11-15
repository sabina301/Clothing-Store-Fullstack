package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/changeuser/{id}")
    public ResponseEntity changeUserRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(roleService.changeUserRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/changeadmin/{id}")
    public ResponseEntity changeAdminRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(roleService.changeAdminRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
