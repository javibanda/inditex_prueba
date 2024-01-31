package com.inditex.zarachallenge.service;

import com.inditex.zarachallenge.model.entity.Product;

public interface ProductService {

    Product getSimilarProducts(Long productId);
}
