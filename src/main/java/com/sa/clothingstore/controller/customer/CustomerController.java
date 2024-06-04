package com.sa.clothingstore.controller.customer;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.customer.CustomerRequest;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.model.customer.Customer;
import com.sa.clothingstore.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(APIConstant.CUSTOMERS)
@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @GetMapping(APIConstant.SEARCH)
    public List<Customer> searchCustomer(@RequestParam("keyword") String keyword){
        return customerService.searchCustomer(keyword);
    }
    @GetMapping(APIConstant.CUSTOMER_ID)
    public Customer getCustomerById(@PathVariable UUID customerId){
        return customerService.getCustomerById(customerId);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        customerService.createCustomer(customerRequest);
        return "Customer was created successfully";
    }
    @PutMapping(APIConstant.CUSTOMER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomer(@PathVariable UUID customerId,@RequestBody @Valid CustomerRequest customerRequest){
        customerService.updateCustomer(customerId , customerRequest);
        return "Customer was modified successfully";
    }
    @DeleteMapping(APIConstant.CUSTOMER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(@PathVariable UUID customerId){
        customerService.deleteCustomer(customerId);
        return "Customer was deleted successfully";
    }
    @PostMapping(APIConstant.CREATE_ADDRESS)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomerAddress(@PathVariable UUID userId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.createAddress(userId, addressRequest);
        return "Customer address created successfully";
    }
    @PutMapping(APIConstant.UPDATE_ADDRESS)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomerAddress(@PathVariable UUID addressId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.updateAddress(addressId ,addressRequest);
        return "Customer address modified successfully";
    }

}
