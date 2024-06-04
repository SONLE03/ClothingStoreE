package com.sa.clothingstore.dto.request.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CartRequest {
    @NotNull(message = "Missing product")
    private UUID productItemId;
    @NotNull(message = "Missing quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
