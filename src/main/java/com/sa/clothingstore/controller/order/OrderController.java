package com.sa.clothingstore.controller.order;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.dto.response.order.OrderItemResponse;
import com.sa.clothingstore.dto.response.order.OrderResponse;
import com.sa.clothingstore.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(APIConstant.ORDERS)
@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping(APIConstant.ORDER_STATUS)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrderByStatus(@PathVariable Integer orderStatus){
        return orderService.getOrderByStatus(orderStatus);
    }
    @GetMapping(APIConstant.ORDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemResponse> getOrderDetail(@PathVariable UUID orderId){
        return orderService.getOrderDetail(orderId);
    }

    @GetMapping(APIConstant.ORDER_CUSTOMER)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrderByCustomer(@PathVariable UUID customerId){
        return orderService.getAllOrderByCustomer(customerId);
    }
    @PostMapping(APIConstant.ORDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String sendOrderToCustomer(@PathVariable UUID orderId){
        orderService.sendOrder(orderId);
        return "The order has been sent to the customer";
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createOrder(@RequestBody @Valid OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
//        return "Order was created successfully";
    }
    @PutMapping(APIConstant.ORDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateOrderStatus(@PathVariable UUID orderId){
        orderService.updateOrderStatusByCash(orderId);
        return "Order was modified successfully";
    }

    @DeleteMapping(APIConstant.ORDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String cancelOrder(@PathVariable UUID orderId){
        orderService.cancelOrder(orderId);
        return "Order was canceled successfully";
    }
}
