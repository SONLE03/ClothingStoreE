package com.sa.clothingstore.dto.response.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse extends CookieResponse {
    @JsonProperty("id")
    private UUID userId;
    @JsonProperty("role")
    private String role;
    @JsonProperty("authority")
    private List<?> authorities;
    @JsonProperty("access")
    private String access;
    @JsonProperty("refresh")
    private String refresh;

    public AuthenticationResponse(UUID userId, Object accessCookie, Object refreshCookie, String role, List<?> authorities, String access, String refresh){
        super(accessCookie, refreshCookie);
        this.userId = userId;
        this.role = role;
        this.authorities = authorities;
        this.access = access;
        this.refresh = refresh;
    }
}
