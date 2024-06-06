package com.sa.clothingstore.service.user.service;

import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.model.user.customer.Address;

import java.util.List;
import java.util.UUID;

public interface CustomerService extends UserService {
    List<Address> getAddressByCustomer(UUID customerId);
    void createAddress(UUID userId, AddressRequest addressRequest);
    void updateAddress(UUID customerId, UUID addressId, AddressRequest addressRequest);
    void deleteAddress(UUID addressId);
}
