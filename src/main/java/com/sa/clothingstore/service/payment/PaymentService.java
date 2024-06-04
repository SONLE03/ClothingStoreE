package com.sa.clothingstore.service.payment;

import com.sa.clothingstore.dto.request.payment.PaymentRequest;
import com.sa.clothingstore.dto.response.payment.PaymentResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<PaymentResponse> getAllPaymentMethod();
    PaymentResponse getPaymentMethodById();
    void createPaymentMethod(PaymentRequest paymentRequest) throws IOException;
    void updatePaymentMethod(Integer paymentId, PaymentRequest paymentRequest) throws IOException;
    void deletePaymentMethod(Integer paymentId);
}
