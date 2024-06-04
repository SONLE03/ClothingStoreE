package com.sa.clothingstore.repository.importInvoice;

import com.sa.clothingstore.dto.response.importProduct.ImportItemResponse;
import com.sa.clothingstore.dto.response.report.DailyExpenseResponse;
import com.sa.clothingstore.dto.response.report.MonthlyExpenseResponse;
import com.sa.clothingstore.dto.response.report.YearlyExpenseResponse;
import com.sa.clothingstore.dto.response.report.YearlyRevenueResponse;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.model.importInvoice.ImportItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ImportItemRepository extends JpaRepository<ImportItem, UUID> {
    List<ImportItem> findByImportInvoice(ImportInvoice importInvoice);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.importProduct.ImportItemResponse(" +
            "i.productItem.id, i.quantity, i.price, i.total) " +
            "FROM ImportItem i WHERE i.importInvoice.id = :importId")
    List<ImportItemResponse> getImportItem(UUID importId);
    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.DailyExpenseResponse(" +
            "i.importInvoice.createdAt, " +
            "COUNT(DISTINCT i.importInvoice.id), " +
            "SUM(i.quantity), " +
            "SUM(i.total)) " +
            "FROM ImportItem i " +
            "WHERE  i.importInvoice.updatedBy = :userId " +
            "AND i.importInvoice.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY i.importInvoice.createdAt " +
            "ORDER BY i.importInvoice.createdAt"
    )
    List<DailyExpenseResponse> getDailyExpenseByUser(UUID userId, Date startDate, Date endDate);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.DailyExpenseResponse(" +
            "i.importInvoice.createdAt, " +
            "COUNT(DISTINCT i.importInvoice.id), " +
            "SUM(i.quantity), " +
            "SUM(i.total)) " +
            "FROM ImportItem i " +
            "WHERE i.importInvoice.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY i.importInvoice.createdAt " +
            "ORDER BY i.importInvoice.createdAt"
    )
    List<DailyExpenseResponse> getDailyExpense(Date startDate, Date endDate);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.MonthlyExpenseResponse(" +
            "MONTH(i.importInvoice.createdAt), " +
            "YEAR(i.importInvoice.createdAt), " +
            "COUNT(DISTINCT i.importInvoice.id), " +
            "SUM(i.quantity), " +
            "SUM(i.total) " +
            ")" +
            "FROM ImportItem i " +
            "WHERE YEAR(i.importInvoice.createdAt) = :year " +
            "GROUP BY MONTH(i.importInvoice.createdAt), YEAR(i.importInvoice.createdAt)" +
            "ORDER BY YEAR(i.importInvoice.createdAt) DESC, MONTH(i.importInvoice.createdAt) DESC"
    )
    List<MonthlyExpenseResponse> getMonthlyExpense(int year);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.YearlyExpenseResponse(" +
            "YEAR(i.importInvoice.createdAt), " +
            "COUNT(DISTINCT i.importInvoice.id), " +
            "SUM(i.quantity), " +
            "SUM(i.total)) " +
            "FROM ImportItem i " +
            "WHERE YEAR(i.importInvoice.createdAt) BETWEEN :startYear AND :endYear " +
            "GROUP BY YEAR(i.importInvoice.createdAt) " +
            "ORDER BY YEAR(i.importInvoice.createdAt) DESC")
    List<YearlyExpenseResponse> getYearlyExpense(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

}
