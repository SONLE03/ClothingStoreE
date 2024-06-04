package com.sa.clothingstore.model.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
    CASH("CASH"),
    VNPAY("VNPAY");
    private final String paymentMethod;
    public static PaymentMethod convertIntegerToPaymentMethod(int status) {
        return switch (status) {
            case 0 -> PaymentMethod.CASH;
            case 1 -> PaymentMethod.VNPAY;
            default -> null;
        };
    }
}
