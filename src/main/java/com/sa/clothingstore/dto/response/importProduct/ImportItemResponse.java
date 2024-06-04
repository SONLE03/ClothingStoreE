package com.sa.clothingstore.dto.response.importProduct;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.model.product.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportItemResponse {
    @JsonProperty("productItem")
    private UUID productItem;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("total")
    private BigDecimal total;
}
