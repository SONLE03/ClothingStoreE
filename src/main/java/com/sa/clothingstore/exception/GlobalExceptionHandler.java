package com.sa.clothingstore.exception;

import com.sa.clothingstore.config.LoggingAspect;
import com.sa.clothingstore.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.io.IOException;
import org.springframework.validation.FieldError;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
    // Global
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex) {
        logger.info("Exception: " + ex.getMessage());
        return "Unknow";
    }

    // Auth exception
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(AuthenticationException authenticationException){
        return authenticationException.getMessage();
    }
    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(DisabledException ex) {
        return "The account has been banned";
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(UsernameNotFoundException ex) {
        return "The username not found";
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(BadCredentialsException ex) {
        return "Invalid username or password";
    }

    // Validate data
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    // Business exception
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(ObjectNotFoundException objectNotFoundException){
        return objectNotFoundException.getMessage();
    }
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(ObjectAlreadyExistsException objectAlreadyExistsException){
        return objectAlreadyExistsException.getMessage();
    }
    @ExceptionHandler(PasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePasswordException(PasswordException passwordException){
        return passwordException.getMessage();
    }
    @ExceptionHandler(OtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleOtpException(OtpException otpException){
        return otpException.getMessage();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException businessException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(businessException.getApiStatus().name());
        errorResponse.setMessage(businessException.getApiStatus().getMessage());
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        return ResponseEntity.status(businessException.getApiStatus().getStatus()).body(errorResponse);
    }
}
