package com.example.pricing.demo.controllers;

import com.example.pricing.demo.dtos.PricingDTO;
import com.example.pricing.demo.model.Pricing;
import com.example.pricing.demo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@RestController
public class PricingController {

    @GetMapping("/pricing")
    public PricingDTO list() {
        List<Pricing> pricingList = setupData();
        List<String> products =
                pricingList
                        .stream()
                        .map(price -> price.getProduct().getName())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());

        Map<String, Map<String, BigDecimal>> pricingByProduct =
                pricingList
                        .stream()
                        .collect(groupingBy(entry -> entry.getProduct().getName(),
                                 toMap(Pricing::getPackageSize, Pricing::getPrice)));

        PricingDTO dto = new PricingDTO();
        dto.products = products;
        dto.pricing = pricingByProduct;

        return dto;
    }


    private List<Pricing> setupData() {
        Product pepsi = new Product(1L, "Pepsi");
        Product kinley = new Product(2L, "Kinley");
        Pricing pepsi1000 = new Pricing(1L, pepsi, "1000ml", new BigDecimal(50));
        Pricing pepsi2000 = new Pricing(2L, pepsi, "2000ml", new BigDecimal(100));
        Pricing kinley1000 = new Pricing(3L, kinley, "1000ml", new BigDecimal(15.0));
        Pricing kinley5000 = new Pricing(4L, kinley, "5000ml", new BigDecimal(50.0));

        return Arrays.asList(pepsi1000, pepsi2000, kinley1000, kinley5000);
    }
}
/*
{
"products":["Pepsi", "Kinley"],
"pricing": {
 "Pepsi":{"1000ml":50, "2000ml": 100},
 "Kinley":{"1000ml":15, "5000ml":50}
 }
}

{
    "products": [
        "Kinley",
        "Pepsi"
    ],
    "pricing": {
        "Kinley": {
            "5000ml": 50.60000000000000142108547152020037174224853515625,
            "1000ml": 15
        },
        "Pepsi": {
            "2000ml": 100,
            "1000ml": 50
        }
    }
}
 */