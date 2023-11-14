package com.myclothingstore.backend.controller;

import com.myclothingstore.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;



}
