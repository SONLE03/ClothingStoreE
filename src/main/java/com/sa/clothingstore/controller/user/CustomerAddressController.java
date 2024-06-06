package com.sa.clothingstore.controller.user;
import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.model.user.customer.Address;
import com.sa.clothingstore.service.user.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RequestMapping(APIConstant.ADDRESS)
@RestController
@AllArgsConstructor
public class CustomerAddressController {
    private final CustomerService customerService;
    @GetMapping(APIConstant.CUSTOMER_ID)
    public List<Address> getAllCustomerAddress(@PathVariable UUID customerId){
        return customerService.getAddressByCustomer(customerId);
    }
    @PostMapping(APIConstant.CUSTOMER_ID)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomerAddress(@PathVariable UUID customerId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.createAddress(customerId, addressRequest);
        return "Customer address created successfully";
    }
    @PutMapping(APIConstant.CUSTOMER_ADDRESS)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomerAddress(@PathVariable UUID customerId, @PathVariable UUID addressId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.updateAddress(customerId, addressId ,addressRequest);
        return "Customer address modified successfully";
    }
    @DeleteMapping(APIConstant.ADDRESS_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomerAddress(@PathVariable UUID addressId){
        customerService.deleteAddress(addressId);
        return "Customer address deleted successfully";
    }
}
