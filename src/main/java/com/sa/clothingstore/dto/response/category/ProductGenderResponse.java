package com.sa.clothingstore.dto.response.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGenderResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("gender_name")
    private String name;
}
