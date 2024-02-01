package com.inditex.zarachallenge.service;

import com.inditex.zarachallenge.exception.HTTPStatusException;

import java.math.BigDecimal;

public interface OfferService {

    BigDecimal getPriceFromProduct(Long productId) throws HTTPStatusException;

}
