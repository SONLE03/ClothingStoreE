package com.sa.clothingstore.controller.email;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.email.EmailRequest;
import com.sa.clothingstore.dto.request.user.ChangePasswordRequest;
import com.sa.clothingstore.exception.OtpException;
import com.sa.clothingstore.model.user.ForgotPassword;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.ForgotPasswordRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.email.EmailService;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;


@RestController
@RequestMapping(APIConstant.EMAIL)
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping(APIConstant.SEND_OTP)
    @ResponseStatus(HttpStatus.OK)
    public String sendOtp(@PathVariable String email){
        return emailService.verifyEmail(email);
    }
}
