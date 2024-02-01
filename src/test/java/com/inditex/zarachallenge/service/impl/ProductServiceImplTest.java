package com.inditex.zarachallenge.service.impl;

import com.inditex.zarachallenge.exception.HTTPStatusException;
import com.inditex.zarachallenge.feign.SimilarIdsFeign;
import com.inditex.zarachallenge.model.dto.ProductDTO;
import com.inditex.zarachallenge.model.entity.Product;
import com.inditex.zarachallenge.model.entity.SizeProduct;
import com.inditex.zarachallenge.repository.ProductRepository;
import com.inditex.zarachallenge.service.OfferService;
import com.inditex.zarachallenge.util.UtilDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository repository;
    @Mock
    private SimilarIdsFeign feignClient;
    @Mock
    private OfferService offerService;

    private static final String DATE = "2023-11-24T12:40:01.773Z";


    @Test
    public void testGetSimilarProducts() throws HTTPStatusException {
        List<Long> similarProductIds = List.of(2L, 3L);
        when(feignClient.getSimilarProductIds(anyLong())).thenReturn(similarProductIds);
        when(repository.findById(anyLong())).thenReturn(Optional.of(product()));
        when(offerService.getPriceFromProduct(anyLong())).thenReturn(BigDecimal.ONE);

        List<ProductDTO> result = productService.getSimilarProducts(1L);

        verify(feignClient).getSimilarProductIds(1L);

        assertEquals(2, result.size());
    }


    @Test(expected = HTTPStatusException.class)
    public void testGetProductDTOWithProductNotFound() throws HTTPStatusException {
        List<Long> similarProductIds = List.of(2L, 3L);
        when(feignClient.getSimilarProductIds(anyLong())).thenReturn(similarProductIds);

        productService.getSimilarProducts(1L);
    }

    private Product product(){
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        SizeProduct sizeProduct = new SizeProduct();
        sizeProduct.setSizeId(1L);
        sizeProduct.setLastUpdated(UtilDate.parseTimeStamp(DATE));
        sizeProduct.setAvailability(Boolean.TRUE);
        product.setSize(sizeProduct);
        return product;
    }


}