package com.techservicetask.demo.service;

import com.techservicetask.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductsIsLess5(Long value);
    Product addProduct(Product product);
    void removeProduct(Long id);
    Product updateProduct(Product product);
}
