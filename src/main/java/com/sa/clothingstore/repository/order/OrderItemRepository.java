package com.sa.clothingstore.repository.order;

import com.sa.clothingstore.dto.response.order.OrderItemResponse;
import com.sa.clothingstore.dto.response.report.DailyRevenueResponse;
import com.sa.clothingstore.dto.response.report.MonthlyRevenueResponse;
import com.sa.clothingstore.dto.response.report.YearlyRevenueResponse;
import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Query("SELECT NEW com.sa.clothingstore.dto.response.order.OrderItemResponse(oi.productItem.id, oi.productItem.product.product_Name, oi.quantity, oi.price, oi.total) " +
            "FROM OrderItem oi " +
            "WHERE oi.order = ?1")
    List<OrderItemResponse> getOrderDetail(Order order);
    @Query("SELECT oi " +
            "FROM OrderItem oi " +
            "WHERE oi.order = ?1")
    List<OrderItem> getOrderItemByOrder(Order order);
    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.DailyRevenueResponse(" +
            "o.order.completedAt, " +
            "COUNT(DISTINCT o.order.customer.id), " +
            "COUNT(DISTINCT o.order.id), " +
            "SUM(o.quantity), " +
            "SUM(o.total)) " +
            "FROM OrderItem o " +
            "WHERE o.order.orderStatus = 'COMPLETED' " +
            "AND o.order.completedAt BETWEEN :startDate AND :endDate " +
            "AND o.order.updatedBy = :userId " +
            "GROUP BY o.order.completedAt " +
            "ORDER BY o.order.completedAt"
    )
    List<DailyRevenueResponse> getDailyRevenueByUser(UUID userId, Date startDate, Date endDate);
    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.DailyRevenueResponse(" +
            "o.order.completedAt, " +
            "COUNT(DISTINCT o.order.customer.id), " +
            "COUNT(DISTINCT o.order.id), " +
            "SUM(o.quantity), " +
            "SUM(o.total)) " +
            "FROM OrderItem o " +
            "WHERE o.order.orderStatus = 'COMPLETED' " +
            "AND o.order.completedAt BETWEEN :startDate AND :endDate " +
            "GROUP BY o.order.completedAt " +
            "ORDER BY o.order.completedAt"
    )
    List<DailyRevenueResponse> getDailyRevenue(Date startDate, Date endDate);

    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.MonthlyRevenueResponse(" +
            "MONTH(o.order.completedAt), " +
            "YEAR(o.order.completedAt), " +
            "COUNT(DISTINCT o.order.customer.id), " +
            "COUNT(DISTINCT o.order.id), " +
            "SUM(o.quantity), " +
            "SUM(o.total)) " +
            "FROM OrderItem o " +
            "WHERE o.order.orderStatus = 'COMPLETED' " +
            "AND YEAR(o.order.completedAt) = :year " +
            "GROUP BY YEAR(o.order.completedAt), MONTH(o.order.completedAt) " +
            "ORDER BY YEAR(o.order.completedAt), MONTH(o.order.completedAt)")
    List<MonthlyRevenueResponse> getMonthlyRevenue(int year);
    @Query("SELECT NEW com.sa.clothingstore.dto.response.report.YearlyRevenueResponse(" +
            "YEAR(o.order.completedAt), " +
            "COUNT(DISTINCT o.order.customer.id), " +
            "COUNT(DISTINCT o.order.id), " +
            "SUM(o.quantity), " +
            "SUM(o.total)) " +
            "FROM OrderItem o " +
            "WHERE o.order.orderStatus = 'COMPLETED' " +
            "AND YEAR(o.order.completedAt) BETWEEN :startYear AND :endYear " +
            "GROUP BY YEAR(o.order.completedAt) " +
            "ORDER BY YEAR(o.order.completedAt) DESC"
    )
    List<YearlyRevenueResponse> getYearlyRevenue(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);
}
