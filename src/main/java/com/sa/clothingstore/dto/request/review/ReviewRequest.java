package com.sa.clothingstore.dto.request.review;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;
@Getter
public class ReviewRequest {
    @NotNull(message = "Missing product")
    private UUID productId;
    @NotNull(message = "Missing customer")
    private UUID customerId;
    @NotNull(message = "Missing content")
    private String content;
}
