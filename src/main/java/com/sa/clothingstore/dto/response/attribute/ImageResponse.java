package com.sa.clothingstore.dto.response.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ImageResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("url")
    private String url;
}
