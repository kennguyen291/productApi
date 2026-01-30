package com.example.product_api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    @Transactional
    public Product fullUpdate(Long id, Product details) {
        Product existing = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        existing.setName(details.getName());
        existing.setPrice(details.getPrice());
        existing.setDiscount(details.isDiscount());
        existing.setQuantity(details.getQuantity());
        
        return repo.save(existing);
    }

    @Transactional
    public void updateRestockStatus(Long id, boolean status) {
        Product p = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        p.setNeedsRestock(status);
        repo.save(p);
    }
}