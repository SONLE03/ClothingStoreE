package com.sa.clothingstore.dto.request.attribute;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageRequest {
    @NotEmpty(message = "Missing URL")
    private String url;
}
