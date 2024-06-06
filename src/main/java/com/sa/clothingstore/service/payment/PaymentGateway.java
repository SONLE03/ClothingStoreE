package com.sa.clothingstore.service.payment;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentGateway {
    String processPayment(HttpServletRequest request, int amount, String orderInfor, String urlReturn);
}
