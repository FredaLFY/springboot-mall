package com.freda.springbootmall.dao.impl;

import com.freda.springbootmall.constant.ProductCategory;
import com.freda.springbootmall.dao.ProductDao;
import com.freda.springbootmall.dto.ProductQueryParams;
import com.freda.springbootmall.dto.ProductRequest;
import com.freda.springbootmall.model.Product;
import com.freda.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT " +
                "product_id, product_name, category, image_url, price, " +
                "stock, description, created_date, last_modified_date " +
                "FROM product WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        ProductCategory category = productQueryParams.getCategory();
        if (category != null) {
            sql += "AND category = :category ";
            map.put("category", category.name());
        }

        String search = productQueryParams.getSearch();
        if (search != null) {
            sql += "AND product_name LIKE :search ";
            map.put("search", "%" + search + "%");
        }

        // 排序
        sql += "ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort() + " ";

        // 分頁
        sql += "LIMIT :limit OFFSET :offset ";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT " +
                "product_id, product_name, category, image_url, price, " +
                "stock, description, created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.isEmpty()) {
            return null;
        } else {
            return productList.getFirst();
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (" +
                "product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date" +
                ") VALUES (" +
                ":product_name, :category, :image_url, :price, :stock, " +
                ":description, :created_date, :last_modified_date" +
                ")";

        Map<String, Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product " +
                "SET product_name = :product_name, " +
                "    category = :category, " +
                "    image_url = :image_url, " +
                "    price = :price, " +
                "    stock = :stock, " +
                "    description = :description, " +
                "    last_modified_date = :last_modified_date " +
                "WHERE product_id = :product_id";

        Map<String, Object> map = new HashMap<>();
        map.put("product_id", productId);

        map.put("product_name", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("last_modified_date", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
