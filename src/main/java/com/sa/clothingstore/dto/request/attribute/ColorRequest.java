package com.sa.clothingstore.dto.request.attribute;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColorRequest {
    @NotEmpty(message = "Missing color name")
    private String name;
}
