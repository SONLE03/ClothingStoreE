package com.sa.clothingstore.controller.payment;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.controller.order.OrderController;
import com.sa.clothingstore.service.order.OrderService;
import com.sa.clothingstore.util.vnpay.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(APIConstant.VNPAY)
@RestController
@AllArgsConstructor
public class VNPayController {
    private final VNPayService vnPayService;
    private final OrderService orderService;
    @PostMapping(APIConstant.SUBMIT_ORDER)
    public String submitOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              @RequestParam("orderId") String orderId,
                              HttpServletRequest request){
        String orderInfoWithUUID = orderInfo + " " + orderId.toString();
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.doPost(request, orderTotal, orderInfoWithUUID, baseUrl);
        return vnpayUrl;
    }
    @GetMapping(APIConstant.VNPAY_RETURN)
    public String paymentCompleted(HttpServletRequest request, Model model){
        int paymentStatus = vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        int uuidIndex = orderInfo.lastIndexOf(" ");
        String extractedUUID = orderInfo.substring(uuidIndex + 1);
        UUID orderId = UUID.fromString(extractedUUID);
        orderService.updateOrderStatusVNPay(orderId, paymentStatus);
        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
