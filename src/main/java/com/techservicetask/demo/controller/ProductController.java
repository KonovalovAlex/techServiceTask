package com.techservicetask.demo.controller;

import com.techservicetask.demo.controller.exception.customException.CustomException;
import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<List<Product>> showProducts(@RequestParam Long less) {
       List<Product> list = productService.findProductsIsLess5(less);
       return ResponseEntity.ok(list);
    }

    //need fix
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody Product productFromBody) {
        if (productFromBody != null) {
            Product product = new Product();
            product.setName(productFromBody.getName());
            product.setBrand(productFromBody.getBrand());
            product.setPrice(productFromBody.getPrice());
            product.setQuantity(productFromBody.getQuantity());
            product = productService.addProduct(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void removeProduct(@PathVariable @Min(1) Long id){
        productService.removeProduct(id);
    }


}

