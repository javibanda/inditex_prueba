package com.inditex.zarachallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SimilarController {
    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<String> getSimilarProducts(@PathVariable Integer productId){
        return ResponseEntity.ok("ok");
    }

}
