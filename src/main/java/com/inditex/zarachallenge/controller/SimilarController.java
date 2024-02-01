package com.inditex.zarachallenge.controller;

import com.inditex.zarachallenge.exception.HTTPStatusException;
import com.inditex.zarachallenge.model.dto.ProductDTO;
import com.inditex.zarachallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SimilarController {

    private final ProductService productService;

    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<List<ProductDTO>> getSimilarProducts(@PathVariable Integer productId){
        try {
            List<ProductDTO> products = productService.getSimilarProducts(productId.longValue());
            return ResponseEntity.ok(products);
        } catch (HTTPStatusException e) {
            return ResponseEntity.status(e.getStatusCode().code()).body(Collections.emptyList());
        }
    }
}
