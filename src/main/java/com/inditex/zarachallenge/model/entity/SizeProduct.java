package com.inditex.zarachallenge.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "SIZE")
@Setter
@Getter
public class SizeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;

    private String size;

    private Boolean availability;

    private Timestamp lastUpdated;

}
