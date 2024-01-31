package com.inditex.zarachallenge.controller;

import com.inditex.zarachallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimilarController {

    private final ProductService productService;
    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<String> getSimilarProducts(@PathVariable Integer productId){
        val products = productService.getProducts(productId);
        return ResponseEntity.ok("ok");
    }

}
