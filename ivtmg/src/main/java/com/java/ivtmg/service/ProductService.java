package com.java.ivtmg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.java.ivtmg.model.Product;
import com.java.ivtmg.repository.ProductRepo;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> findByIvtmgId(Integer ivtmgId, Pageable pageable) {
        return productRepo.findByIvtmgId(ivtmgId, pageable);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    // You can add more service methods as needed for your application

}
