package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.model.DTO.LoginResponseDTO;
import com.myclothingstore.backend.model.DTO.RegistrationDTO;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    private ResponseEntity userRegister(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        authenticationService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
        return ResponseEntity.ok("Сохранен");
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
