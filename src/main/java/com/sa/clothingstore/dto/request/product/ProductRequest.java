package com.sa.clothingstore.dto.request.product;

import com.sa.clothingstore.dto.request.attribute.ImageRequest;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class ProductRequest {
    @NotEmpty(message = "Missing product name")
    private String product_Name;
    private String description;
    @NotNull(message = "Missing price")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "Missing category")
    private UUID category;
    @NotNull(message = "Missing branch")
    private UUID branch;
    private List<ProductItemRequest> productItemRequests;
}
