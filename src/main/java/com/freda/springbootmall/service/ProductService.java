package com.freda.springbootmall.service;

import com.freda.springbootmall.dto.ProductRequest;
import com.freda.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
