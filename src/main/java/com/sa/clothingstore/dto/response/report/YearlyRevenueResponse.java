package com.sa.clothingstore.dto.response.report;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
public class YearlyRevenueResponse {
    private int year;
    private long totalCustomers;
    private long totalOrders;
    private long totalProductsSold;
    private BigDecimal totalRevenue;
}
