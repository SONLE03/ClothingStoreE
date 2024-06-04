package com.sa.clothingstore.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "Missing customer")
    private UUID customerId;
    private UUID coupon;
    @NotNull(message = "Missing payment method")
    private Integer paymentMethod;
    List<OrderItemRequest> orderItemRequestList;
    private String note;
}
