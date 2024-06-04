package com.sa.clothingstore.service.report;

import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.request.report.MonthlyRequest;
import com.sa.clothingstore.dto.request.report.YearlyRequest;
import com.sa.clothingstore.dto.response.report.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<DailyRevenueResponse> getDailyRevenueByUser(UUID userId, Date date);
    List<DailyExpenseResponse> getDailyExpenseByUser(UUID userId, Date date);

    List<DailyRevenueResponse> getDailyRevenue(DailyRequest date);
    List<DailyExpenseResponse> getDailyExpense(DailyRequest date);

    List<MonthlyRevenueResponse> getMonthlyRevenue(MonthlyRequest year);
    List<MonthlyExpenseResponse> getMonthlyExpense(MonthlyRequest year);
    List<YearlyRevenueResponse> getYearlyRevenue(YearlyRequest years);
    List<YearlyExpenseResponse> getYearlyExpense(YearlyRequest years);

}
