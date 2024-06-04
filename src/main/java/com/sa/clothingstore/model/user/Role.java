package com.sa.clothingstore.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("admin"),
    STAFF("staff"),
    CUSTOMER("customer");
    private final String role;
    public static Role convertIntegerToRole(int role) {
        return switch (role) {
            case 0 -> Role.ADMIN;
            case 1 -> Role.STAFF;
            case 2 -> Role.CUSTOMER;
            default -> null;
        };
    }
}
