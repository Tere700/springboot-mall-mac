package com.example.springbootmallmac.service.impl;

import com.example.springbootmallmac.dao.ProductDao;
import com.example.springbootmallmac.model.Product;
import com.example.springbootmallmac.service.ProductService;
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
