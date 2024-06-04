package com.sa.clothingstore.dto.response.order;

import com.sa.clothingstore.model.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderResponse {
    private UUID orderId;
    private Date orderDate;
    private BigDecimal total;
    private UUID customerId;
    private String customerName;
    private String customerPhone;
    private OrderStatus status;
}
