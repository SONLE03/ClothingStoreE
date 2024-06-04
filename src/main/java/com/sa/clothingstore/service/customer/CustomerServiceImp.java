package com.sa.clothingstore.service.customer;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.customer.CustomerRequest;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.model.customer.Address;
import com.sa.clothingstore.model.customer.Customer;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.repository.customer.AddressRepository;
import com.sa.clothingstore.repository.customer.CustomerRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final UserDetailService userDetailService;

    @Override
    @Transactional
    public List<Address> getAddressByCustomer(UUID customerId){
        return null;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomer(String keyword) {
        return customerRepository.searchCustomer(keyword);
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
    }

    @Override
    public void createCustomer(CustomerRequest customerRequest) {
        if(customerRepository.findByPhone(customerRequest.getPhone()) != null){
            throw new BusinessException(APIStatus.PHONE_ALREADY_EXISTED);
        }
        Customer customer = Customer.builder()
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .fullName(customerRequest.getFullName())
                .build();
        customer.setCommonCreate(userDetailService.getIdLogin());
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerRequest request) {
        String newPhone = request.getPhone();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        Customer customerRequest = customerRepository.findByPhone(newPhone);
        if(customerRequest != null && customer.getId() != customerRequest.getId() && newPhone != customer.getPhone()){
            throw new BusinessException(APIStatus.PHONE_ALREADY_EXISTED);
        }
        customer.setPhone(newPhone);
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setCommonUpdate(userDetailService.getIdLogin());
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new BusinessException(APIStatus.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public void createAddress(UUID userId, AddressRequest addressRequest) {
        Customer customer = customerRepository.findById(userId).orElseThrow(
                () -> new ObjectNotFoundException("Customer not found"));
        Address address = Address.builder()
                .postalCode(addressRequest.getPostalCode())
                .ward(addressRequest.getWard())
                .specificAddress(addressRequest.getSpecificAddress())
                .district(addressRequest.getDistrict())
                .province(addressRequest.getProvince())
                .phone(addressRequest.getPhone())
                .isDefault(addressRequest.isDefault())
                .customer(customer)
                .build();
        addressRepository.save(address);
    }
    @Override
    @Transactional
    public void updateAddress(UUID addressId, AddressRequest addressRequest){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer address not found"));
        address.setPostalCode(addressRequest.getPostalCode());
        address.setWard(addressRequest.getWard());
        address.setSpecificAddress(addressRequest.getSpecificAddress());
        address.setDistrict(addressRequest.getDistrict());
        address.setProvince(addressRequest.getProvince());
        address.setPhone(addressRequest.getPhone());
        address.setDefault(addressRequest.isDefault());
        addressRepository.save(address);
    }
}
