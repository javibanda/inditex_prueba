package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.exception.ExceptionMessage;
import com.inditex.zarachallenge.exception.HTTPStatusException;
import com.inditex.zarachallenge.feign.SimilarIdsFeign;
import com.inditex.zarachallenge.mapper.ProductMapper;
import com.inditex.zarachallenge.model.dto.ProductDTO;
import com.inditex.zarachallenge.model.entity.Product;
import com.inditex.zarachallenge.repository.ProductRepository;
import com.inditex.zarachallenge.service.OfferService;
import com.inditex.zarachallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.mockserver.model.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final SimilarIdsFeign feignClient;
    private final OfferService offerService;

    @Override
    public List<ProductDTO> getSimilarProducts(Long productId) throws HTTPStatusException {
        List<Long> similarProductIds = feignClient.getSimilarProductIds(productId);
        return getProductDTOS(similarProductIds);
    }

    private ProductDTO getProductDTO(Long productId) throws HTTPStatusException {
        Optional<Product> product = repository.findById(productId);
        if (product.isEmpty()){
            throw new HTTPStatusException(ExceptionMessage.PRODUCT_NOT_FOUND, HttpStatusCode.NOT_FOUND_404);
        }
        BigDecimal productPrice = offerService.getPriceFromProduct(productId);
        return ProductMapper.toProductDTO(product.get(), productPrice);
    }

    private List<ProductDTO> getProductDTOS(List<Long> productIds) throws HTTPStatusException {
        List<ProductDTO> products = new ArrayList<>();
        for (Long productId: productIds){
            products.add(getProductDTO(productId));
        }
        return products;
    }
}
