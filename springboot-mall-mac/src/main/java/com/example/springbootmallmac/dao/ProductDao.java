package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.constant.ProductCategory;
import com.example.springbootmallmac.dto.ProductRequest;
import com.example.springbootmallmac.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
