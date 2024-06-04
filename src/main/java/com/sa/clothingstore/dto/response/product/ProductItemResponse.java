package com.sa.clothingstore.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemResponse {
    private UUID id;
    private String sizeName;
    private String colorName;
    private int quantity;
    private BigDecimal price;

    // Constructors, getters, and setters
}
