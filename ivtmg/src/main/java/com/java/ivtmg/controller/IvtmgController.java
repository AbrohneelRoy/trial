package com.java.ivtmg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.java.ivtmg.model.Ivtmg;
import com.java.ivtmg.model.Product;
import com.java.ivtmg.service.IvtmgService;
import com.java.ivtmg.service.ProductService;

@RestController
@RequestMapping("/api")
public class IvtmgController {
    @Autowired
    private IvtmgService ivtmgService;

    @Autowired
    private ProductService productService;

    @PostMapping("/user")
    public ResponseEntity<Ivtmg> addIvtmg(@RequestBody Ivtmg ivtmg) {
        Ivtmg addedIvtmg = ivtmgService.addIvtmg(ivtmg);
        if (addedIvtmg != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedIvtmg);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Page<Ivtmg>> getAllIvtmgs(Pageable pageable) {
        Page<Ivtmg> ivtmgs = ivtmgService.getAllIvtmgs(pageable);
        if (!ivtmgs.isEmpty()) {
            return ResponseEntity.ok(ivtmgs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Ivtmg> getIvtmgById(@PathVariable Integer userId) {
        Ivtmg ivtmg = ivtmgService.getIvtmgById(userId);
        if (ivtmg != null) {
            return ResponseEntity.ok(ivtmg);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Ivtmg> updateIvtmg(@PathVariable Integer userId, @RequestBody Ivtmg ivtmg) {
        if (ivtmgService.getIvtmgById(userId) != null) {
            ivtmg.setId(userId);
            Ivtmg updatedIvtmg = ivtmgService.updateIvtmg(ivtmg);
            return ResponseEntity.ok(updatedIvtmg);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteIvtmgById(@PathVariable Integer userId) {
        if (ivtmgService.getIvtmgById(userId) != null) {
            ivtmgService.deleteIvtmgById(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get users by username with pagination
    @GetMapping("/user/by-username")
    public ResponseEntity<Page<Ivtmg>> findByUsername(@RequestParam String username, Pageable pageable) {
        Page<Ivtmg> users = ivtmgService.findByUsername(username, pageable);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user/{userId}/product")
    public ResponseEntity<Product> addProductToIvtmg(@PathVariable Integer userId, @RequestBody Product product) {
        Ivtmg ivtmg = ivtmgService.getIvtmgById(userId);
        
        if (ivtmg != null) {
            product.setIvtmg(ivtmg);
            Product addedProduct = productService.addProduct(product);
            
            if (addedProduct != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}/products")
    public ResponseEntity<Page<Product>> getProductsByIvtmg(@PathVariable Integer userId, Pageable pageable) {
        // Check if the Ivtmg exists to provide a meaningful error if it does not.
        if (!ivtmgService.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }

        Page<Product> products = productService.findByIvtmgId(userId, pageable);
        
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(products);
    }
}
