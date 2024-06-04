package com.sa.clothingstore.service.report;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.request.report.MonthlyRequest;
import com.sa.clothingstore.dto.request.report.YearlyRequest;
import com.sa.clothingstore.dto.response.report.*;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.repository.importInvoice.ImportItemRepository;
import com.sa.clothingstore.repository.order.OrderItemRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportServiceImp implements ReportService{
    private final UserDetailService userDetailService;
    private final OrderItemRepository orderRepository;
    private final ImportItemRepository importItemRepository;

    @Override
    public List<DailyRevenueResponse> getDailyRevenueByUser(UUID userId, Date date) {
        if(userDetailService.getIdLogin() != userId){
            throw new BusinessException(APIStatus.USER_NOT_FOUND);
        }
        orderRepository.getDailyRevenueByUser(userId, date, new Date());
        return null;
    }

    @Override
    public List<DailyExpenseResponse> getDailyExpenseByUser(UUID userId, Date date) {
        if(userDetailService.getIdLogin() != userId){
            throw new BusinessException(APIStatus.USER_NOT_FOUND);
        }
        importItemRepository.getDailyExpenseByUser(userId, date, new Date());
        return null;
    }

    @Override
    public List<DailyRevenueResponse> getDailyRevenue(DailyRequest date) {
        return orderRepository.getDailyRevenue(date.getStartDate(), date.getEndDate());
    }

    @Override
    public List<DailyExpenseResponse> getDailyExpense(DailyRequest date) {
        return importItemRepository.getDailyExpense(date.getStartDate(), date.getEndDate());
    }

    @Override
    public List<MonthlyRevenueResponse> getMonthlyRevenue(MonthlyRequest year) {
        return orderRepository.getMonthlyRevenue(year.getYear());
    }

    @Override
    public List<YearlyRevenueResponse> getYearlyRevenue(YearlyRequest years) {
        return orderRepository.getYearlyRevenue(years.getStartYear(), years.getEndYear());
    }

    @Override
    public List<MonthlyExpenseResponse> getMonthlyExpense(MonthlyRequest year) {
        return importItemRepository.getMonthlyExpense(year.getYear());
    }

    @Override
    public List<YearlyExpenseResponse> getYearlyExpense(YearlyRequest years) {
        return importItemRepository.getYearlyExpense(years.getStartYear(), years.getEndYear());
    }
}
