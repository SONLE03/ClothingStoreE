package com.sa.clothingstore.service.customer;

import com.sa.clothingstore.dto.request.customer.CustomerRequest;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.model.customer.Address;
import com.sa.clothingstore.model.customer.Customer;
import com.sa.clothingstore.service.user.service.UserService;

import java.util.List;
import java.util.UUID;


public interface CustomerService{
    List<Address> getAddressByCustomer(UUID customerId);
    List<Customer> getAllCustomer();
    List<Customer> searchCustomer(String keyword);
    Customer getCustomerById(UUID customerId);
    void createCustomer(CustomerRequest customerRequest);
    void updateCustomer(UUID customerId, CustomerRequest customerRequest);
    void deleteCustomer(UUID customerId);
    void createAddress(UUID userId, AddressRequest addressRequest);
    void updateAddress(UUID addressId, AddressRequest addressRequest);
}
