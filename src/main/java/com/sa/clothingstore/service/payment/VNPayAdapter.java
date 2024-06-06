package com.sa.clothingstore.service.payment;

import com.sa.clothingstore.util.vnpay.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class VNPayAdapter implements PaymentGateway {
    private final VNPayService vnpayService;

    public VNPayAdapter(VNPayService vnpayService) {
        this.vnpayService = vnpayService;
    }

    @Override
    public String processPayment(HttpServletRequest request, int amount, String orderInfor, String urlReturn) {
        return vnpayService.doPost(request, amount, orderInfor, urlReturn);
    }
}