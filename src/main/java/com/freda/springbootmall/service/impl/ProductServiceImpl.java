package com.freda.springbootmall.service.impl;

import com.freda.springbootmall.dao.ProductDao;
import com.freda.springbootmall.model.Product;
import com.freda.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
