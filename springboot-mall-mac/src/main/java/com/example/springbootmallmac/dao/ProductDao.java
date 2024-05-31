package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
