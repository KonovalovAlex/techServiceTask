package com.techservicetask.demo.controller;

import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Value("${jwt.header}")
    private String tokenHeader;
    private Product product;

    @GetMapping(value = "/show/{value}")
//    @ExceptionHandler(CustomException.class)
    public ResponseEntity<List<Product>> showProducts(@PathVariable("value") Integer value) {
       List<Product> list = productService.findProductsIsLess5(value);
       return ResponseEntity.ok(list);
    }

    //need fix
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
 //   @ExceptionHandler(CustomException.class)
    public ResponseEntity.BodyBuilder addProduct(@RequestBody Product productFromBody) {
        if (productFromBody != null) {
            Product product = new Product();
            product.setName(productFromBody.getName());
            product.setBrand(productFromBody.getBrand());
            product.setPrice(productFromBody.getPrice());
            product.setQuantity(productFromBody.getQuantity());
            productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //need fix
    @DeleteMapping
    public void removeProduct(@RequestBody Product product){
        productService.removeProduct(product);
    }


}

