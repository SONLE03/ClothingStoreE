package com.sa.clothingstore.service.order;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.order.OrderItemRequest;
import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.dto.response.order.OrderItemResponse;
import com.sa.clothingstore.dto.response.order.OrderResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.model.order.*;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.customer.Customer;
import com.sa.clothingstore.repository.event.CouponRepository;
import com.sa.clothingstore.repository.order.OrderItemRepository;
import com.sa.clothingstore.repository.order.OrderRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.repository.customer.CustomerRepository;
import com.sa.clothingstore.service.email.EmailService;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService{
    private final UserDetailService userDetailService;
    private final CustomerRepository customerRepository;
    private final ProductItemRepository productItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CouponRepository couponRepository;
    private final EmailService emailService;

    @Override
    public List<OrderResponse> getAllOrder() {
        return orderRepository.getAllOrder();
    }

    @Override
    public List<OrderResponse> getOrderByStatus(Integer status) {
        OrderStatus orderStatus = OrderStatus.convertIntegerToOrderStatus(status);
        if(orderStatus == null){
            throw new BusinessException(APIStatus.ORDER_STATUS_NOT_FOUND);
        }
        return orderRepository.getOrderByStatus(orderStatus);
    }

    @Override
    public List<OrderResponse> getAllOrderByCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        return orderRepository.getOrderByCustomer(customer);
    }

    @Override
    public List<OrderItemResponse> getOrderDetail(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        return orderItemRepository.getOrderDetail(order);
    }

    @Transactional
    @Override
    public UUID createOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        Coupon coupon;
        var couponId = orderRequest.getCoupon();
        if(couponId == null){
            coupon = null;
        }else{
            coupon = couponRepository.findById(couponId).orElse(null);
        }
        BigDecimal _total = BigDecimal.ZERO;;
        BigDecimal couponValue = BigDecimal.ZERO;
        if(coupon != null && coupon.getQuantity() > 0){
            couponValue = coupon.getDiscountValue().negate();
            coupon.setQuantity(coupon.getQuantity() - 1);
            coupon.setCommonUpdate(userDetailService.getIdLogin());
            couponRepository.save(coupon);
        }else{
//            _total = BigDecimal.ZERO;
        }
        Order order = Order.builder()
                .note(orderRequest.getNote())
                .customer(customer)
                .coupon(coupon)
                .build();
        orderRepository.save(order);
        List<OrderItemRequest> orderItemList = orderRequest.getOrderItemRequestList();
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest item : orderItemList){
            ProductItem productItem = productItemRepository.findById(item.getProductItemId()).orElseThrow(
                    () -> new BusinessException(APIStatus.PRODUCT_ITEM_NOT_FOUND));
            Integer quantity = item.getQuantity();
            if(productItem.getQuantity() < quantity){
                throw new BusinessException(APIStatus.INSUFFICIENT_PRODUCT_QUANTITY);
            }
            BigDecimal price = productItem.getProduct().getPrice();
            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));

            _total = _total.add(total);

            OrderItemKey orderItemKey = new OrderItemKey();
            orderItemKey.setOrderId(order.getId());
            orderItemKey.setProductItemId(productItem.getId());

            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemKey);
            orderItem.setOrder(order);
            orderItem.setProductItem(productItem);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setTotal(total);

            productItem.setQuantity(productItem.getQuantity() - quantity);
            productItemRepository.save(productItem);

            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
        _total = _total.add(_total.multiply(couponValue.divide(BigDecimal.valueOf(100))));
        order.setTotal(_total);
        order.setCommonCreate(userDetailService.getIdLogin());
        PaymentMethod paymentMethod = PaymentMethod.convertIntegerToPaymentMethod(orderRequest.getPaymentMethod());
        if(paymentMethod == PaymentMethod.CASH){
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setCompletedAt(CommonModel.resultTimestamp());
            sendOrder(order.getId());
        }else{
            order.setOrderStatus(OrderStatus.PENDING);
        }
        order.setPaymentMethod(paymentMethod);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    @Transactional
    public void updateOrderStatusByCash(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        order.setPaymentMethod(PaymentMethod.CASH);
        order.setCompletedAt(CommonModel.resultTimestamp());
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setCommonUpdate(userDetailService.getIdLogin());
        sendOrder(orderId);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderStatusVNPay(UUID orderId, Integer status) {
        final Integer paymentSuccess = 1;
        final Integer paymentFail = 0;
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        if (status == paymentSuccess) {
            order.setCompletedAt(CommonModel.resultTimestamp());
            order.setOrderStatus(OrderStatus.COMPLETED);
        } else {
            order.setCanceledAt(CommonModel.resultTimestamp());
            order.setOrderStatus(OrderStatus.CANCELED);
        }
        orderRepository.save(order);
        sendOrder(orderId);
    }
    @Override
    @Transactional
    public void sendOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        emailService.sendOrder(order);
    }

    @Override
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        Set<OrderItem> orderItemSet = order.getOrderItems();
        for(OrderItem item : orderItemSet){
            ProductItem productItem = productItemRepository.findById(item.getProductItem().getId()).orElseThrow(
                    () -> new BusinessException(APIStatus.PRODUCT_ITEM_NOT_FOUND));
            productItem.setQuantity(productItem.getQuantity() + item.getQuantity());
            productItemRepository.save(productItem);
        }
        order.setCanceledAt(CommonModel.resultTimestamp());
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setCommonUpdate(userDetailService.getIdLogin());
        orderRepository.save(order);
    }
}
