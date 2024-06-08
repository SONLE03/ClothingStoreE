package com.sa.clothingstore.dto.request.user;

import com.sa.clothingstore.constant.validation.PhoneNumberFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateRequest {
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    @NotEmpty(message = "Missing user full name")
    private String fullName;
    @Length(max = 8, message = "Nickname length must be less than or equal to 8")
    private String nickName;
}
