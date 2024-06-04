package com.sa.clothingstore.dto.response.report;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
public class YearlyExpenseResponse {
    private int year;
    private long totalInvoices;
    private long totalProducts;
    private BigDecimal totalExpense;
}
