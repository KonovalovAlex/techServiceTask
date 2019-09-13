package com.techservicetask.demo.service;

import com.techservicetask.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductsIsLess5(Integer value);
    Product addProduct(Product product);
    void removeProduct(Product product);
    Product updateProduct(Product product);
}
