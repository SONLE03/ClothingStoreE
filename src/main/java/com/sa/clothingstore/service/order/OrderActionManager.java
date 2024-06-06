package com.sa.clothingstore.service.order;

import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderActionManager {
    private OrderActionStrategy actionStrategy;

    public void setActionStrategy(OrderActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void executeAction(Order order) {
        actionStrategy.updateOrderStatus(order);
    }
}
