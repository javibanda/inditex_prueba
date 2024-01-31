package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.feign.SimilarIdsFeign;
import com.inditex.zarachallenge.model.entity.Product;
import com.inditex.zarachallenge.repository.ProductRepository;
import com.inditex.zarachallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final SimilarIdsFeign feignClient;
    private final ProductRepository repository;
    @Override
    public Product getSimilarProducts(Long productId) {
        val similarProductIds = feignClient.getSimilarProductIds(productId);
        return getProductDTO(productId);
    }

    private Product getProductDTO(Long productId){
        val product = repository.findById(productId);
        return product.orElse(null);
    }
}
