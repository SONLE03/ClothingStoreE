package com.sa.clothingstore.dto.request.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemRequest {
    @NotNull(message = "Missing product")
    private UUID productItemId;
    @NotNull(message = "Missing quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
