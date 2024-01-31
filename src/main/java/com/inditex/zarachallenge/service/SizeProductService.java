package com.inditex.zarachallenge.service;

import com.inditex.zarachallenge.model.entity.SizeProduct;

public interface SizeProductService {

    SizeProduct getSizeProduct(Long id);

    void update(SizeProduct sizeProduct);
}
