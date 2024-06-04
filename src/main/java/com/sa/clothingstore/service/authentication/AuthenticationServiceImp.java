package com.sa.clothingstore.service.authentication;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.authentication.AuthenticationRequest;
import com.sa.clothingstore.dto.request.authentication.RegisterRequest;
import com.sa.clothingstore.dto.response.authentication.AuthenticationResponse;
import com.sa.clothingstore.dto.response.authentication.CookieResponse;
import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.user.RefreshToken;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.model.customer.Customer;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.repository.customer.CustomerRepository;
import com.sa.clothingstore.service.token.JwtService;
import com.sa.clothingstore.service.token.RefreshTokenService;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;

    @Override
    @Transactional
    public User signup(RegisterRequest registerRequest) {
        userRepository.findByEmail(registerRequest.getEmail()).ifPresent(user -> {
            throw new BusinessException(APIStatus.EMAIL_ALREADY_EXISTED);
        });
        userRepository.findByPhone(registerRequest.getPhone()).ifPresent(user -> {
            throw new BusinessException(APIStatus.PHONE_ALREADY_EXISTED);
        });
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setFullName(registerRequest.getFullname());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.ADMIN);
        user.setCommonCreate(userDetailService.getIdLogin());
        userRepository.save(user);
        return user;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
       authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
       var user = userRepository.findByEmail(authenticationRequest.getUsername())
               .orElseThrow(() -> new BusinessException(APIStatus.USER_NOT_FOUND)
               );
       return this.generateToken(user);
    }
    @Override
    public CookieResponse signout(){
        Object principle = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principle.toString().equals("anonymousUser")) {
            UUID userId = ((User) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }
        return CookieResponse.builder()
                .accessCookie(jwtService.getCleanJwtCookie())
                .refreshCookie(jwtService.getCleanJwtRefreshCookie())
                .build();
    }

    @Override
    public Optional<ResponseCookie> refreshToken(HttpServletRequest request) {
        String refreshToken = jwtService.getJwtRefreshFromCookies(request);
        System.out.println(refreshToken);
        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtService.generateJwtCookie(user);
                        return jwtCookie;
                    });
        }
        return null;
    }

    @Override
    public UserResponse me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return modelMapper.map(currentUser, UserResponse.class);
    }

    private AuthenticationResponse generateToken(User user){
//        ResponseCookie jwtCookie = jwtService.generateJwtCookie(user);
        String jwt = jwtService.generateTokenFromUsername(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user.getId());
        ResponseCookie jwtRefreshCookie = jwtService.generateRefreshJwtCookie(refreshToken.getToken());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(user.getId(), jwt, refreshToken.getToken(), user.getRole().toString(), Arrays.asList(user.getAuthorities().toArray()), jwt, refreshToken.getToken());
        return authenticationResponse;
    }


}
