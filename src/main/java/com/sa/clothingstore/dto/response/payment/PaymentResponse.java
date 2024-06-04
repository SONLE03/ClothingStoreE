package com.sa.clothingstore.dto.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String image;
    @JsonProperty
    private String name;
}
