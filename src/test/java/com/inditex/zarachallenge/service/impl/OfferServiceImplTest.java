package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.exception.HTTPStatusException;
import com.inditex.zarachallenge.model.entity.Offer;
import com.inditex.zarachallenge.repository.OfferRepository;
import com.inditex.zarachallenge.util.UtilDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    @Mock
    private OfferRepository repository;

    @InjectMocks
    private OfferServiceImpl offerService;

    private static final Long SAMPLE_PRODUCT_ID = 1L;
    private static final String SAMPLE_CURRENT_DATE = "2023-11-24T12:40:01.773Z";

    @Before
    public void setUp() {
        setPrivateField(offerService);
    }

    @Test
    public void should_get_price_from_getPriceFromProduct() throws HTTPStatusException {
        Offer existingOffer = new Offer();
        existingOffer.setPrice(BigDecimal.valueOf(20));
        when(repository.findFirstByProductIdAndValidFromBeforeOrderByValidFromDesc(
                anyLong(), any())).thenReturn(existingOffer);

        BigDecimal result = offerService.getPriceFromProduct(SAMPLE_PRODUCT_ID);

        verify(repository).findFirstByProductIdAndValidFromBeforeOrderByValidFromDesc(
                eq(SAMPLE_PRODUCT_ID), eq(UtilDate.parseTimeStamp(SAMPLE_CURRENT_DATE)));

        assertEquals(BigDecimal.valueOf(20), result);
    }

    @Test(expected = HTTPStatusException.class)
    public void should_throw_exception_when_getPriceFromProduct() throws HTTPStatusException {
        when(repository.findFirstByProductIdAndValidFromBeforeOrderByValidFromDesc(
                anyLong(), any())).thenReturn(null);
        offerService.getPriceFromProduct(SAMPLE_PRODUCT_ID);
    }

    private static void setPrivateField(Object target) {
        String fieldName = "CURRENT_DATE";
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, SAMPLE_CURRENT_DATE);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error setting private field: " + fieldName, e);
        }
    }

}