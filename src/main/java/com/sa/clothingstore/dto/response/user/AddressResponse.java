package com.sa.clothingstore.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressResponse {
    private UUID addressId;
    private String province;
    private String district;
    private String ward;
    private String specificAddress;
    private String postalCode;
}
