package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.exception.UserNotFoundException;
import com.myclothingstore.backend.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/changeuser/{id}")
    public ResponseEntity changeUserRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(roleService.changeUserRoleService(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/changeadmin/{id}")
    public ResponseEntity changeAdminRoleController(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(roleService.changeAdminRoleService(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
