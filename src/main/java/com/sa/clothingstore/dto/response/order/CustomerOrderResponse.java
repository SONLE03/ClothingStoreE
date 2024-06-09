package com.sa.clothingstore.dto.response.order;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerOrderResponse {
    private BigDecimal totalAmount;
    private Long distinctProductItemCount;
    private Long totalQuantity;
}
