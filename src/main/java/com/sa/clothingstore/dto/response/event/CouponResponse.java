package com.sa.clothingstore.dto.response.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.model.event.EventStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CouponResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("discountValue")
    private BigDecimal discountValue;
    @JsonProperty("minimumBill")
    private BigDecimal minimumBill;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("status")
    private String eventStatus;
}
