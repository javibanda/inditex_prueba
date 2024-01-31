package com.inditex.zarachallenge.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private Float number;
    private boolean availability;
}
