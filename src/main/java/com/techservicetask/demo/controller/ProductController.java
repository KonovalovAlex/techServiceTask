package com.techservicetask.demo.controller;

import com.techservicetask.demo.controller.customException.CustomException;
import com.techservicetask.demo.entity.Product;
import com.techservicetask.demo.service.ProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController(value = "/product")
@Setter
public class ProductController {

    private final ProductService productService;
    @Value("${quantityValue}")
    private Integer quantityValue;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //localhost:8080/product?less=5
    @GetMapping
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<List<Product>> showProducts(@RequestParam Long less) {

        if (less < quantityValue) {
            List<Product> list = productService.findProductsIsLess(less);
            return ResponseEntity.ok(list);
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> addProduct(@RequestBody Product productFromBody) {
        if (productFromBody != null) {
            Product product = new Product();
            product.setName(productFromBody.getName());
            product.setBrand(productFromBody.getBrand());
            product.setPrice(productFromBody.getPrice());
            product.setQuantity(productFromBody.getQuantity());
            product = productService.addProduct(product);
            return ResponseEntity.ok("product was created id=" +product.getId());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    @ExceptionHandler(CustomException.class)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productFromBody) {
        if (productFromBody != null) {
            productFromBody.setName(productFromBody.getName());
            productFromBody.setBrand(productFromBody.getBrand());
            productFromBody.setPrice(productFromBody.getPrice());
            productFromBody.setQuantity(productFromBody.getQuantity());
            Product product = productService.updateProduct(productFromBody);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void removeProduct(@RequestParam Long id) {
        productService.removeProduct(id);
    }
}

