package com.freda.springbootmall.dao;

import com.freda.springbootmall.dto.ProductRequest;
import com.freda.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
