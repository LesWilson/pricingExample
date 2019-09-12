package com.example.pricing.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Pricing {
    Long id;
    Product product;
    String packageSize;
    BigDecimal price;
}
