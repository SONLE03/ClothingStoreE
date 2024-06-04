package com.sa.clothingstore.dto.request.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.constant.validation.FutureOfDateFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Data
public class DailyRequest {
    @JsonProperty("startDate")
    @NotNull(message = "Start date must not be null")
    private Date startDate;
    @JsonProperty("endDate")
    @NotNull(message = "End date must not be null")
    private Date endDate;

    @AssertTrue(message = "End date must be after start date")
    private boolean isValidEndDate() {
        return !endDate.before(startDate);
    }
}
