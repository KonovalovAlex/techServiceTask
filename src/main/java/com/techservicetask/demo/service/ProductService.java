package com.techservicetask.demo.service;

import com.techservicetask.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductsIsLess(Long value);
    Product addProduct(Product product);
    void removeProduct(Long id);
    Product updateProduct(Product product);
}
