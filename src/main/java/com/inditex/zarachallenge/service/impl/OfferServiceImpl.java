package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.repository.OfferRepository;
import com.inditex.zarachallenge.service.OfferService;
import com.inditex.zarachallenge.util.UtilDate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final static String CURRENT_DATE = "2023-11-24T12:40:01.773Z";
    private final OfferRepository repository;

    @Override
    public BigDecimal getPriceFromProduct(Long productId) {
        val offer =  repository.findFirstByProductIdAndValidFromBeforeOrderByValidFromDesc(
                productId, UtilDate.parseTimeStamp(CURRENT_DATE));
        if (offer == null){
            return null;
        }
        return offer.getPrice();
    }
}
