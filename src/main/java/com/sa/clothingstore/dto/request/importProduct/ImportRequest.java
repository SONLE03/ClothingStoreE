package com.sa.clothingstore.dto.request.importProduct;

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
public class ImportRequest {
    @NotNull(message = "Missing product")
    private UUID productItemId;
    @NotNull(message = "Missing quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
    @NotNull(message = "Missing price")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "Missing price")
    @DecimalMin(value = "0.00", inclusive = false, message = "Total must be greater than 0")
    private BigDecimal total;

}
