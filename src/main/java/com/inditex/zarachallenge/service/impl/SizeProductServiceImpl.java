package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.model.entity.SizeProduct;
import com.inditex.zarachallenge.repository.SizeProductRepository;
import com.inditex.zarachallenge.service.SizeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeProductServiceImpl implements SizeProductService {

    private final SizeProductRepository repository;
    @Override
    public SizeProduct getSizeProduct(Long id) {
        return repository.findBySizeId(id);
    }

    @Override
    public void update(SizeProduct sizeProduct) {
        repository.save(sizeProduct);
    }
}
