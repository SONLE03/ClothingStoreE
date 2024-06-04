package com.sa.clothingstore.dto.response.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CartResponse {
    private UUID productItemId;
    private String sizeName;
    private String colorName;
    private Integer quantity;
    private BigDecimal price;
    private String product_Name;
}
