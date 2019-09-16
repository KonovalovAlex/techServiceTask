package com.techservicetask.demo.service.impl;

import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.repository.ProductRepository;
import com.techservicetask.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW )
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW )
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Product> findProductsIsLess(Long value) {
        return productRepository.findByQuantityIsLessThan(value);
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
