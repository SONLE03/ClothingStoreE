package com.sa.clothingstore.service.payment;

import com.paypal.base.rest.PayPalRESTException;
import com.sa.clothingstore.util.paypal.PayPalService;
import com.sa.clothingstore.util.vnpay.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class PayPalAdapter implements PaymentGateway{
    private final PayPalService payPalService;
    public PayPalAdapter(PayPalService payPalService) {
        this.payPalService = payPalService;
    }
    @Override
    public String processPayment(HttpServletRequest request, int amount, String orderInfor, String urlReturn) {
        try {
            return payPalService.createPayment(amount, "USD", "PayPal", "sale", orderInfor);
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
    }

}
