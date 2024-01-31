package com.inditex.zarachallenge.mapper;

import com.inditex.zarachallenge.infrastructure.model.ProductAvailabilityEvent;
import com.inditex.zarachallenge.model.entity.SizeProduct;

public class SizeProductMapper {

    public static SizeProduct updateEntity(ProductAvailabilityEvent productAvailability, SizeProduct entity){
        entity.setAvailability(productAvailability.isAvailability());
        entity.setLastUpdated(productAvailability.getUpdate());
        return entity;
    }
}
