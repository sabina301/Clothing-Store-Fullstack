package com.myclothingstore.backend.controller;

import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
@RestController
public class PageController {

    private final ResourceLoader resourceLoader;

    public PageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @GetMapping("/auth/login")
    public Resource getLoginPage() {
        return resourceLoader.getResource("classpath:/static/login.html");
    }
    @GetMapping("/auth/signup")
    public Resource getSignupPage() {
        return resourceLoader.getResource("classpath:/static/signup.html");
    }
    @GetMapping("/main")
    public Resource getMainPage() {
        return resourceLoader.getResource("classpath:/static/index.html");
    }
    @GetMapping("/catalog/woman")
    public Resource getCatalogWomanPage() {
        return resourceLoader.getResource("classpath:/static/catalog.html");
    }
    @GetMapping("/catalog/man")
    public Resource getCatalogManPage(){
        return resourceLoader.getResource("classpath:/static/catalog.html");
    }
    @GetMapping("/profile")
    public Resource getProfilePage(){
        return resourceLoader.getResource("classpath:/static/profile.html");
    }
    @GetMapping("/cart")
    public Resource getCartPage(){
        return resourceLoader.getResource("classpath:/static/cart.html");
    }
    @GetMapping("/product/{id}")
    public Resource getProductPage(){
        return resourceLoader.getResource("classpath:/static/product.html");
    }
}