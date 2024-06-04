package com.sa.clothingstore.dto.request.event;

import com.sa.clothingstore.constant.validation.FutureOfDateFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class CouponRequest {
    @NotEmpty(message = "Missing name")
    private String name;
    @FutureOfDateFormat(message = "Start date must be in the future or today")
    private Date startDate;
    @FutureOfDateFormat(message = "End date must be in the future or today")
    private Date endDate;
    @NotNull(message = "Missing discount value")
    @DecimalMin(value = "0.00", inclusive = false, message = "Discount value must be greater than 0")
    private BigDecimal discountValue;
    @NotNull(message = "Missing minimum bill value")
    @DecimalMin(value = "0.00", inclusive = false, message = "Minimum bill value must be greater than 0")
    private BigDecimal minimumBill;
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    private Integer status;
    @AssertTrue(message = "End date must be after start date")
    private boolean isValidEndDate() {
        return !endDate.before(startDate);
    }
}
