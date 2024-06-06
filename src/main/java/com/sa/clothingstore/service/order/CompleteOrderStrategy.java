package com.sa.clothingstore.service.order;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderStatus;
import com.sa.clothingstore.model.order.PaymentMethod;
import com.sa.clothingstore.repository.order.OrderRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompleteOrderStrategy implements OrderActionStrategy{
    private final OrderRepository orderRepository;
    private final UserDetailService userDetailService;
    @Override
    public void updateOrderStatus(Order order) {
        OrderStatus orderStatus = order.getOrderStatus();
        if(orderStatus == OrderStatus.CANCELED || orderStatus == OrderStatus.PENDING){
            throw new BusinessException(APIStatus.ORDER_NOT_COMPLETE);
        }
        if(order.getPaymentMethod() == PaymentMethod.CASH){
            order.setPaymentAt(CommonModel.resultTimestamp());
        }
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setCompletedAt(CommonModel.resultTimestamp());
        order.setCommonUpdate(userDetailService.getIdLogin());
        orderRepository.save(order);
    }
}
