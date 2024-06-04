package com.sa.clothingstore.dto.request.user;

import com.sa.clothingstore.constant.validation.PhoneNumberFormat;
import com.sa.clothingstore.dto.request.authentication.RegisterRequest;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest{
    @NotEmpty(message = "Missing email")
    @Email(message = "Invalid email")
    private String email;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    @NotEmpty(message = "Missing user full name")
    private String fullName;
    @NotEmpty(message = "Missing password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter")
    private String password;
    @Min(value = 0, message = "Value must be at least 0")
    @Max(value = 1, message = "Value must be at most 1")
    private int enable;
    @Min(value = 0, message = "Role must be at least 0")
    @Max(value = 2, message = "Role must be at most 2")
    private int role;
    private Date dateOfBirth;
    private MultipartFile image;
}
