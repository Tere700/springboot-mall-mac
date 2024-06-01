package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.ProductRequest;
import com.example.springbootmallmac.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}