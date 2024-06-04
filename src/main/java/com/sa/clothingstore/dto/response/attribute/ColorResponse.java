package com.sa.clothingstore.dto.response.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColorResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
}
