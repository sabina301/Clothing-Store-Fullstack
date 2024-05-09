package com.myclothingstore.backend.controller;

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

    @GetMapping("/auth/login")
    public ResponseEntity<byte[]> getLoginPage() throws IOException {
        Resource resource = new ClassPathResource("static/login.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/auth/signup")
    public ResponseEntity<byte[]> getSignupPage() throws IOException {
        Resource resource = new ClassPathResource("static/signup.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/main")
    public ResponseEntity<byte[]> getMainPage() throws IOException {
        Resource resource = new ClassPathResource("static/index.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/catalog/woman")
    public ResponseEntity<byte[]> getCatalogWomanPage() throws IOException {
        Resource resource = new ClassPathResource("static/catalog.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/catalog/man")
    public ResponseEntity<byte[]> getCatalogManPage() throws IOException {
        Resource resource = new ClassPathResource("static/catalog.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/profile")
    public ResponseEntity<byte[]> getProfilePage() throws IOException {
        Resource resource = new ClassPathResource("static/profile.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/cart")
    public ResponseEntity<byte[]> getCartPage() throws IOException {
        Resource resource = new ClassPathResource("static/cart.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<byte[]> getProductPage() throws IOException {
        Resource resource = new ClassPathResource("static/product.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(bytes);
    }




}