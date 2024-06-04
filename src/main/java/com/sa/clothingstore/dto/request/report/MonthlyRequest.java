package com.sa.clothingstore.dto.request.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.constant.validation.ValidYear;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MonthlyRequest { // Xem theo tháng
    @JsonProperty("year")
    @ValidYear
    private Integer year;
}