package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.dto.ProductQueryParams;
import com.example.springbootmallmac.dto.ProductRequest;
import com.example.springbootmallmac.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    void updateStock(Integer productId, Integer stock);
}
