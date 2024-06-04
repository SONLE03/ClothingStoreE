package com.sa.clothingstore.dto.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.category.Category;
import com.sa.clothingstore.model.product.ProductStatus;
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
public class ProductResponse {
    @JsonProperty
    private UUID id;
    @JsonProperty
    private String product_Name;
    @JsonProperty
    private String description;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private String category;
    @JsonProperty
    private String branch;
    @JsonProperty
    private ProductStatus productStatus;
    @JsonProperty
    private String image;
}
