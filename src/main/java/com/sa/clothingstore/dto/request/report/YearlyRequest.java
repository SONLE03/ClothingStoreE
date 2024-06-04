package com.sa.clothingstore.dto.request.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class YearlyRequest {
    @JsonProperty("startYear")
    @NotNull(message = "Start date must not be null")
    private Integer startYear;
    @JsonProperty("endYear")
    @NotNull(message = "End date must not be null")
    private Integer endYear;

    @AssertTrue(message = "End date must be after start date")
    private boolean isValidEndDate() {
        return endYear > startYear;
    }
}
