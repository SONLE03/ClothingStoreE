package com.sa.clothingstore.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.constant.validation.PhoneNumberFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse extends AddressResponse{
    @JsonProperty
    private UUID Id;
    @JsonProperty
    private String email;
    @JsonProperty
    private String phone;
    @JsonProperty
    private String fullname;
    @JsonProperty
    private String password;
    @JsonProperty
    private Date dateOfBirth;
    @JsonProperty
    private String role;
    @JsonProperty
    private String image;
    @JsonProperty
    private boolean enable;
}
