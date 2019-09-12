package com.example.pricing.demo.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PricingDTO {

    public List<String> products;

    public Map<String, Map<String, BigDecimal>> pricing;
}


/*
{
"products":["Pepsi", "Kinley"],
"pricing": {
 "Pepsi":{"1000ml":50, "2000ml": 100},
 "Kinley":{"1000ml":15, "5000ml":50}
 }
}
 */