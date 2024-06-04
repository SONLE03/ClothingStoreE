package com.sa.clothingstore.dto.request.authentication;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    @NotEmpty(message = "Missing login information")
    private String username;
    @NotEmpty(message = "Missing login information")
    private String password;
}
