package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.feign.SimilarIdsFeign;
import com.inditex.zarachallenge.mapper.ProductMapper;
import com.inditex.zarachallenge.model.dto.ProductDTO;
import com.inditex.zarachallenge.repository.ProductRepository;
import com.inditex.zarachallenge.service.OfferService;
import com.inditex.zarachallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final SimilarIdsFeign feignClient;
    private final OfferService offerService;

    @Override
    public List<ProductDTO> getSimilarProducts(Long productId) {
        val similarProductIds = feignClient.getSimilarProductIds(productId);
        return similarProductIds.stream()
                .map(this::getProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO getProductDTO(Long productId){
        val product = repository.findById(productId);
        if (product.isEmpty()){
            return null;
        }
        val productPrice = offerService.getPriceFromProduct(productId);
        return ProductMapper.toProductDTO(product.get(), productPrice);
    }
}
