package com.sa.clothingstore.model.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("STOP SELLING"),
    DELETED("DELETED");
    private final String productStatus;
}
