package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.exception.ExceptionMessage;
import com.inditex.zarachallenge.exception.HTTPStatusException;
import com.inditex.zarachallenge.model.entity.Offer;
import com.inditex.zarachallenge.repository.OfferRepository;
import com.inditex.zarachallenge.service.OfferService;
import com.inditex.zarachallenge.util.UtilDate;
import lombok.RequiredArgsConstructor;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    @Value("${date}")
    private String CURRENT_DATE;
    private final OfferRepository repository;

    @Override
    public BigDecimal getPriceFromProduct(Long productId) throws HTTPStatusException {
        Offer offer =  repository.findFirstByProductIdAndValidFromBeforeOrderByValidFromDesc(
                productId, UtilDate.parseTimeStamp(CURRENT_DATE));
        if (offer == null){
            throw new HTTPStatusException(ExceptionMessage.OFFER_NOT_FOUND, HttpStatusCode.NOT_FOUND_404);
        }
        return offer.getPrice();
    }
}
