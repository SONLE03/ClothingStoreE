package com.sa.clothingstore.dto.response.order;

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
public class OrderItemResponse {
    private UUID productItem;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;
}
