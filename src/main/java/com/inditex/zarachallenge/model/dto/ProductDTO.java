package com.inditex.zarachallenge.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private BigDecimal price;
    private Boolean availability;
}
