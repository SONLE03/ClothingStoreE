package com.sa.clothingstore.model.event;

import com.sa.clothingstore.model.user.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {
    ACTIVE("ACTIVE"),
    EXPIRED("EXPIRED"),
    DISABLED("DISABLED");
    private final String eventStatus;
    public static EventStatus convertIntegerToStatus(int status) {
        return switch (status) {
            case 0 -> EventStatus.ACTIVE;
            case 1 -> EventStatus.EXPIRED;
            case 2 -> EventStatus.DISABLED;
            default -> null;
        };
    }
}
