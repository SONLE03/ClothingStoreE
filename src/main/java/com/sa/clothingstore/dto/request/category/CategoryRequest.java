package com.sa.clothingstore.dto.request.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    @NotEmpty(message = "Missing category name")
    private String name;
    @NotNull(message = "Missing product gender")
    private UUID productGender;
}
