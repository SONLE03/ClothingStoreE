package com.sa.clothingstore.service.order;

import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.dto.response.order.OrderItemResponse;
import com.sa.clothingstore.dto.response.order.OrderResponse;
import com.sa.clothingstore.dto.response.report.MonthlyRevenueResponse;
import com.sa.clothingstore.model.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderResponse> getAllOrder();
    List<OrderResponse> getOrderByStatus(Integer status);
    List<OrderResponse> getAllOrderByCustomer(UUID customerId);
    List<OrderItemResponse> getOrderDetail(UUID orderId);
    UUID createOrder(OrderRequest orderRequest);
    void updateOrderStatusByCash(UUID orderId);
    void updateOrderStatusVNPay(UUID orderId, Integer status);
    void sendOrder(UUID orderId);
    void cancelOrder(UUID orderId);
}

