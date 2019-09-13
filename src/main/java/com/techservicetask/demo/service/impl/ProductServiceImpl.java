package com.techservicetask.demo.service.impl;

import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.repository.ProductRepository;
import com.techservicetask.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Scope("prototype")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findProductsIsLess5(Integer value) {
        return productRepository.findByQuantityIsLessThan(value);
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
