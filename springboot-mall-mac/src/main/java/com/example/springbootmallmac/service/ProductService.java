package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.ProductQueryParams;
import com.example.springbootmallmac.dto.ProductRequest;
import com.example.springbootmallmac.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
