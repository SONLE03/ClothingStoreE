package com.sa.clothingstore.service.order.orderStatusStrategy;

import com.sa.clothingstore.model.order.Order;
import org.springframework.stereotype.Component;

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
