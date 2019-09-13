package com.techservicetask.demo.repository;


import com.techservicetask.demo.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByQuantityIsLessThan(Integer quantity);
}
