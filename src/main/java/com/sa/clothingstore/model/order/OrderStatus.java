package com.sa.clothingstore.model.order;
import com.sa.clothingstore.model.user.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("PENDING"),
    CANCELED("CANCELED"),
    DELIVERED("DELIVERED"),
    COMPLETED("COMPLETED"),
    PAID("PAID");

    private final String orderStatus;
    public static OrderStatus convertIntegerToOrderStatus(int status) {
        return switch (status) {
            case 0 -> OrderStatus.PENDING;
            case 1 -> OrderStatus.CANCELED;
            case 2 -> OrderStatus.DELIVERED;
            case 3 -> OrderStatus.COMPLETED;
            case 4 -> OrderStatus.PAID;
            default -> null;
        };
    }
}
