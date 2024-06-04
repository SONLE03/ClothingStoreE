package com.sa.clothingstore.service.authentication;

import com.sa.clothingstore.dto.request.authentication.AuthenticationRequest;
import com.sa.clothingstore.dto.request.authentication.RegisterRequest;
import com.sa.clothingstore.dto.response.authentication.AuthenticationResponse;
import com.sa.clothingstore.dto.response.authentication.CookieResponse;
import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.model.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;

import java.util.Optional;

public interface AuthenticationService {
    User signup(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    CookieResponse signout();
    Optional<ResponseCookie> refreshToken(HttpServletRequest request);
    UserResponse me();

}
