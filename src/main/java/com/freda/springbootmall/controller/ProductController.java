package com.freda.springbootmall.controller;

import com.freda.springbootmall.dto.ProductRequest;
import com.freda.springbootmall.model.Product;
import com.freda.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
        // 檢查 product 是否存在
        Product product = productService.getProductById(productId);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        // 修改商品的數據
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.ok(updateProduct);
    }
}
