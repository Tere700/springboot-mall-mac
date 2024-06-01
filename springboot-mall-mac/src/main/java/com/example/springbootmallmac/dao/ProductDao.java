package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.dto.ProductRequest;
import com.example.springbootmallmac.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
