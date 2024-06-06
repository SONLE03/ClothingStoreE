package com.sa.clothingstore.service.order.orderStatusStrategy;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderItem;
import com.sa.clothingstore.model.order.OrderStatus;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.repository.order.OrderRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelOrderStrategy implements OrderActionStrategy{
    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final UserDetailService userDetailService;

    @Override
    public void updateOrderStatus(Order order) {
        if(order.getOrderStatus() != OrderStatus.PENDING && order.getOrderStatus() != OrderStatus.PAID) {
            throw new BusinessException(APIStatus.ORDER_NOT_CANCEL);
        }
        for(OrderItem items : order.getOrderItems()){
            ProductItem productItem = productItemRepository.findById(items.getProductItem().getId()).orElseThrow(
                    () -> new BusinessException(APIStatus.PRODUCT_ITEM_NOT_FOUND));
            productItem.setQuantity(productItem.getQuantity() + items.getQuantity());
            productItemRepository.save(productItem);
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setCanceledAt(CommonModel.resultTimestamp());
        order.setCommonUpdate(userDetailService.getIdLogin());
        orderRepository.save(order);
    }
}
