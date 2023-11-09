package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/changeuserrole/{id}")
    public ResponseEntity changeUserRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(roleService.changeUserRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/changeadminrole/{id}")
    public ResponseEntity changeAdminRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(roleService.changeAdminRoleService(id));
        } catch (Exception err){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
