package com.freda.springbootmall.dao;

import com.freda.springbootmall.constant.ProductCategory;
import com.freda.springbootmall.dto.ProductRequest;
import com.freda.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
