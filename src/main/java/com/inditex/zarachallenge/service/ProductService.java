package com.inditex.zarachallenge.service;

import com.inditex.zarachallenge.exception.HTTPStatusException;
import com.inditex.zarachallenge.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getSimilarProducts(Long productId) throws HTTPStatusException;
}
