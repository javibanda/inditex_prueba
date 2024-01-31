package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.feign.SimilarIdsFeign;
import com.inditex.zarachallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor public class ProductServiceImpl implements ProductService {
    private final SimilarIdsFeign feignClient;
    @Override
    public List<Long> getProducts(Integer productId) {
        return feignClient.getSimilarProductIds(productId);
    }
}
