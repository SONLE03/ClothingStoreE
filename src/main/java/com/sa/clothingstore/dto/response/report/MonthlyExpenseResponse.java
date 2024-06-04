package com.sa.clothingstore.dto.response.report;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
public class MonthlyExpenseResponse {
    private int month;
    private int year;
    private long totalInvoices;
    private long totalProducts;
    private BigDecimal totalExpense;
}
