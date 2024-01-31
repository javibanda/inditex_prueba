package com.inditex.zarachallenge.mapper;

import com.inditex.zarachallenge.model.dto.ProductDTO;
import com.inditex.zarachallenge.model.entity.Product;

import java.math.BigDecimal;

public class ProductMapper {

    public static ProductDTO toProductDTO(Product entity, BigDecimal productPrice){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(entity.getId().toString());
        productDTO.setName(entity.getName());
        productDTO.setPrice(productPrice);
        productDTO.setAvailability(entity.getSize().getAvailability());
        return productDTO;
    }
}
