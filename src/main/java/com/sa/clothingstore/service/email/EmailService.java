package com.sa.clothingstore.service.email;

import com.sa.clothingstore.dto.request.email.EmailRequest;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.order.Order;

import java.util.UUID;

public interface EmailService {
    void sendSimpleMailMessage(EmailRequest request);

    String verifyEmail(String email);
    Integer generateOtp();
    void sendOrder(Order order);
    void sendImportProduct(ImportInvoice importInvoice);
}
