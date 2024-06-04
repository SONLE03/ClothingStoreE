package com.sa.clothingstore.dto.response.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CookieResponse {
    @JsonProperty("access_cookie")
    private Object accessCookie;
    @JsonProperty("refresh_cookie")
    private Object refreshCookie;
}
