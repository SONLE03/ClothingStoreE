package com.sa.clothingstore.dto.response.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse{
    @JsonProperty("id")
    private UUID userId;
    @JsonProperty("role")
    private String role;
    @JsonProperty("authority")
    private List<?> authorities;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
