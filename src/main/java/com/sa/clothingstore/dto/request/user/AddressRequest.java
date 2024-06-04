package com.sa.clothingstore.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {
    @NotEmpty(message = "Missing phone")
    private String phone;
    @NotEmpty(message = "Missing province")
    private String province;
    @NotEmpty(message = "Missing district")
    private String district;
    @NotEmpty(message = "Missing ward")
    private String ward;
    @NotEmpty(message = "Missing specificAddress")
    private String specificAddress;
    private String postalCode;
    private boolean isDefault;
}
