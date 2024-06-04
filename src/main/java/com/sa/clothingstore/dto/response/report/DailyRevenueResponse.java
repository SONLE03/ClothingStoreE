package com.sa.clothingstore.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyRevenueResponse {
    private Date date;
    private long totalCustomers;
    private long totalOrders;
    private long totalProductsSold;
    private BigDecimal totalRevenue;
}
