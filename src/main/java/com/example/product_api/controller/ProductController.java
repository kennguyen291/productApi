package com.example.product_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.product_api.entity.Product;
import com.example.product_api.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> list() {
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Product> create(@RequestBody Product p) {
        return new ResponseEntity<>(service.create(p), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product p) {
        return ResponseEntity.ok(service.fullUpdate(id, p));
    }

    @PatchMapping("/{id}/restock")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Void> toggleRestock(@PathVariable Long id, @RequestBody boolean status) {
        service.updateRestockStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}